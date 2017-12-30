import angular from 'angular';

import MenuComponent from './menu.component';

const moduleId = 'xronosApp.menu';

angular.module(moduleId, [])
.component('appMenu', MenuComponent);

export default moduleId;
