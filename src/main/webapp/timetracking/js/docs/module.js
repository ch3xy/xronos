import angular from 'angular';
import moment from 'moment';

import docsComponent from './docs.component';
import monthSwitchComponent from './month-switch.component';

import durationFilter from './duration.filter';

const moduleId = 'xronosApp.docs';

angular.module(moduleId, [])
.config(['$stateProvider', ($stateProvider) => {
	$stateProvider.state({
		name:'docs',
		url:'/docs/{year:[0-9]{4,4}}-{month:[0-9]{1,2}}',
		component:'docs',
		resolve:{
			record:['$stateParams', 'docsService', ($stateParams, docsService) => {
				const year = parseInt($stateParams.year);
				const month = parseInt($stateParams.month);
                return docsService.fetchMonth(moment({ year:year, month:month -1 }));
			}],
            dayTypes:['docsService', (docsService) => {
			    return docsService.fetchDayTypes();
            }]
		}
	});
}])
.component('docs', docsComponent)
.component('monthSwitch', monthSwitchComponent)
.filter('duration', durationFilter);

export default moduleId;
