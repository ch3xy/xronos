import angular from 'angular';
import ngAnimate from 'angular-animate';
import ngResource from 'angular-resource';
import uiRouter from '@uirouter/angularjs';
import moment from 'moment';

import 'moment/locale/de-at';
import '../scss/main.scss';

import menuModule from './menu/module';
import docsModule from './docs/module';

import docsService from './service/docs.service';

import datetimeComponent from './common/datetime.component';

const appId = 'xronosApp';

angular.module(appId, [
    uiRouter,
    ngAnimate,
    ngResource,
    menuModule,
    docsModule
])
.config(['$stateProvider', '$urlRouterProvider', ($stateProvider, $urlRouterProvider) => {
    $urlRouterProvider.otherwise(($injector) => {
        $injector.invoke(['$state', ($state) => {
            const now = new Date();
            $state.go('docs', {
                year:now.getFullYear(),
                month:now.getMonth() + 1
            });
        }]);
    });
}])
.service('docsService', docsService)
.component('datetime', datetimeComponent);

angular.bootstrap(document, [appId]);
