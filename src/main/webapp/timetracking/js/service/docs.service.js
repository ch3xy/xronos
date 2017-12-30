import moment from 'moment';

export default ['$http', '$q', function($http, $q) {
    const dateFormat = 'YYYY-MM-DD';

    function fetchDayTypes() {
        return $http.get('/api/timetracking/types').then((response) => response.data);
    }

	function fetchMonth(date) {
	    const monthStart = moment(date).startOf('month').format(dateFormat);
	    const monthEnd = moment(date).endOf('month').format(dateFormat);

	    return $http.get(`/api/timetracking/days?from=${monthStart}&to=${monthEnd}`).then(createMonthDays(date));
    }

    function createMonthDays(date) {
	    const refDate = moment(date).startOf('month');
        const dayNumbers = Array.from(new Array(refDate.daysInMonth()), (val, idx) => idx + 1);
	    return (response) => {
            const mapping = (response.data.days || []).reduce((obj, day) => {
                obj[day.date] = day;
                return obj;
            }, {});
            const days = dayNumbers.map((num) => {
                const currentDate = refDate.clone().date(num);
                const currentDateString = currentDate.format(dateFormat);
                return mapping[currentDateString] || {
                    date:currentDateString,
                    items:[],
                    targetWorkHours:currentDate.isoWeekday() < 6 ? 7.7 : 0,
                    type:'WORKDAY'
                };
            });

            return Object.assign(response.data, {
                days:days
            });
        };
    }

	function saveDay(day) {
        return $http.post(`/api/timetracking/day/${day.date}`, day);
    }

	return {
	    fetchDayTypes:fetchDayTypes,
        fetchMonth:fetchMonth,
        saveDay:saveDay
	};
}];
