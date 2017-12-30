import moment from 'moment';

class Controller {
	constructor($element) {
		this.$element = $element;
	}

	$onChanges(changes) {
		if(changes.date && moment.isMoment(changes.date.currentValue)) {
			this.refDate = changes.date.currentValue.clone();
		}
	}

	$postLink() {
		this.$input = this.$element.find('input');
		this.ngModelCtrl = this.$input.controller('ngModel');

		// view -> model
		this.ngModelCtrl.$parsers.push((viewValue) => {
			return this.createUpdatedModel(viewValue);
		});

		// model -> view
		this.ngModelCtrl.$formatters.push((modelValue) => {
			return moment(modelValue).format(this.format);
		});

		// validation
		this.ngModelCtrl.$validators.time = (modelValue) => {
			return moment(modelValue).isValid();
		};

		// when view value of ng-model is updated
		this.ngModelCtrl.$render = () => {
			this.$input.val(this.ngModelCtrl.$viewValue);
		};

		// propagate user changes back as output
		this.ngModelCtrl.$viewChangeListeners.push(() => {
			if(typeof this.onChange === 'function') {
				this.onChange({ $event:this.ngModelCtrl.$modelValue });
			}
		});
	}

	blur() {
		// NOTE: $processModelValue() is not available yet (in master-snapshot)
		// therefore this way of updating the view on blur to be in sync with model
		const viewValue = moment(this.ngModelCtrl.$modelValue).format(this.format);
		this.ngModelCtrl.$setViewValue(viewValue);
		this.ngModelCtrl.$render();

		if(typeof this.onBlur === 'function') {
		    this.onBlur({ $event:this.ngModelCtrl.$modelValue });
        }
	}

	createUpdatedModel(viewValue) {
		const newMoment = moment(viewValue, this.format);
		const modifiableParts = this.getModifiableParts();
		const dateParts = [
			modifiableParts.year ? newMoment.year() : this.refDate.year(),
			modifiableParts.month ? newMoment.month() : this.refDate.month(),
			modifiableParts.date ? newMoment.date() : this.refDate.date(),
			modifiableParts.hour ? newMoment.hour() : this.refDate.hour(),
			modifiableParts.minute ? newMoment.minute() : this.refDate.minute(),
			modifiableParts.second ? newMoment.second() : this.refDate.second(),
			modifiableParts.millisecond ? newMoment.millisecond() : this.refDate.millisecond()
		];
		return moment(dateParts);
	}

	getModifiableParts() {
		// NOTE: only simple/restricted support
		return {
			year:this.format.indexOf('Y') > -1,
			month:this.format.indexOf('M') > -1,
			date:this.format.indexOf('D') > -1,
			hour:this.format.indexOf('H') > -1,
			minute:this.format.indexOf('m') > -1,
			second:this.format.indexOf('s') > -1,
			millisecond:this.format.indexOf('S') > -1
		};
	}
}

export default {
	controller:Controller,
	template:'<input type="text" ng-model="$ctrl.date" ng-blur="$ctrl.blur()" />',
	bindings:{
		date:'<',
		format:'@',
		onChange:'&',
        onBlur:'&'
	}
};
