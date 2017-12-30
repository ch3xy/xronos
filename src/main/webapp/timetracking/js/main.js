import angular from 'angular';
import ngAnimate from 'angular-animate';
import ngResource from 'angular-resource';
import ngSanitize from 'angular-sanitize';
import uiRouter from '@uirouter/angularjs';

import i18next from 'i18next';
import XHRBackend from 'i18next-xhr-backend';
import Cache from 'i18next-localstorage-cache';
import moment from 'moment';

import 'moment/locale/de-at';
import '../scss/main.scss';

import menuModule from './menu/module';
import docsModule from './docs/module';

import docsService from './service/docs.service';

import datetimeComponent from './common/datetime.component';

const appId = 'xronosApp';
const i18nextOptions = {
    lng:'de',
    fallbackLng:'de',
    backend:{
        loadPath:'/api/translations/{{lng}}'
    },
    useCookie:false,
    useLocalStorage:true
};

angular.module(appId, [
    uiRouter,
    ngAnimate,
    ngResource,
    ngSanitize,
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

i18next
.use(Cache)
.use(XHRBackend)
.init(i18nextOptions, () => {
    angular.bootstrap(document, [appId]);
});
