import moment from 'moment';

import template from './docs.html';

class Controller {
	constructor($stateParams, docsService) {
	    this.docsService = docsService;
		this.year = parseInt($stateParams.year);
		this.month = parseInt($stateParams.month);
		this.currentDate = moment({ year:this.year, month:this.month - 1 });
		this.dateParserFormat = 'YYYY-MM-DD';
		this.timeParserFormat = 'HH:mm';
	}

	$onInit() {
		this.title = this.currentDate.format('MMMM YYYY');
		this.record = this.record || {};
		this.data = this.createData();
	}

	onWorkStartChange(newMoment, day) {
		day.workStart = newMoment;
	}

	onWorkEndChange(newMoment, day) {
		day.workEnd = newMoment;
	}

	onBreakStartChange(newMoment, day) {
		day.breakStart = newMoment;
	}

	onBreakEndChange(newMoment, day) {
		day.breakEnd = newMoment;
	}

    onInputBlur(day) {
        const serverDay = {
            date:day.date.format(this.dateParserFormat),
            items:this.formatTimes(day),
            targetWorkHours:day.targetWorkHours,
            type:day.type
        };
	    this.docsService.saveDay(serverDay).then(this.onSaveSuccess.bind(this), this.onSaveError.bind(this));
    }

    onSaveSuccess() {
        // console.log('save success:', arguments);
    }

    onSaveError() {
	    // console.log('save error:', arguments);
    }

	getCalculatedWorkhours(day) {
		return this.calculateWorkhours(day);
	}

	getNeededWorkhours(day) {
		return moment.duration(day.targetWorkHours, 'hours');
	}

	getDifferenceWorkhours(day) {
		const calculatedWorkhours = this.calculateWorkhours(day);
		const neededWorkhours = moment.duration(day.targetWorkHours, 'hours');
		return moment.duration(calculatedWorkhours - neededWorkhours);
	}

	getCalculatedWeekWorkhours(week) {
		return week.reduce((duration, day) => {
			duration.add(this.getCalculatedWorkhours(day));
			return duration;
		}, moment.duration());
	}

	getNeededWeekWorkhours(week) {
		return week.reduce((duration, day) => {
			duration.add(this.getNeededWorkhours(day));
			return duration;
		}, moment.duration());
	}

	getDifferenceWeekWorkhours(week) {
		return week.reduce((duration, day) => {
			duration.add(this.getDifferenceWorkhours(day));
			return duration;
		}, moment.duration());
	}

	getCalendarWeek(week) {
		if(week instanceof Array && week.length > 0) {
			return 'KW ' + week[0].date.week();
		}
		return 'KW -';
	}

	createData() {
		const days = this.record.days instanceof Array ? this.record.days : [];
		return days.reduce((obj, day) => {
			const date = moment(day.date);
			const week = date.isoWeek();

			if(!obj[week]) {
				obj[week] = [];
			}

			const times = this.parseTimes(date, day);

			obj[week].push(Object.assign(times, {
				date:date,
				type:day.type,
                targetWorkHours:day.targetWorkHours,
                notes:day.items instanceof Array && day.items.length > 0 ? day.items[0].notes : ''
			}));

			return obj;
		}, {});
	}

	parseTimes(refDate, day) {
	    if(day.items instanceof Array && day.items.length === 2) {
            return {
                workStart:this.buildMomentTime(refDate, day.items[0].from),
                breakStart:this.buildMomentTime(refDate, day.items[0].to),
                breakEnd:this.buildMomentTime(refDate, day.items[1].from),
                workEnd:this.buildMomentTime(refDate, day.items[1].to)
            };
        }

	    return {
	        breakStart:refDate.clone(),
            breakEnd:refDate.clone(),
            workStart:refDate.clone(),
            workEnd:refDate.clone()
        };
    }

    formatTimes(day) {
	    return [
            {
                from:day.workStart.format(this.timeParserFormat),
                to:day.breakStart.format(this.timeParserFormat),
                notes:day.notes
            },
            {
                from:day.breakEnd.format(this.timeParserFormat),
                to:day.workEnd.format(this.timeParserFormat),
                notes:day.notes
            }
        ];
    }

    buildMomentTime(refDate, timeString) {
	    const md = moment(timeString, this.timeParserFormat);
        return refDate.clone().add(md.hours(), 'hours').add(md.minutes(), 'minutes');
    }

	calculateWorkhours(day) {
		const a = day.workEnd - day.workStart;
		const b = day.breakEnd - day.breakStart;
		return moment.duration(a - b);
	}

	isDurationNegative(duration) {
		return moment.isDuration(duration) ? duration.asMilliseconds() < 0 : false;
	}

	isWeekend(day) {
		return day.date.isoWeekday() > 5;
	}

	formatDateString(dateString, formatString) {
		return moment(dateString, this.dateParserFormat).format(formatString);
	}
}

export default {
	controller:Controller,
	template:template,
	bindings:{
		record:'<'
	}
};
