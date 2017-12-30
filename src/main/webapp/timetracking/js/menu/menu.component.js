import template from './menu.html';

class Controller {
	$onInit() {
		this.title = 'Xronos (alpha)';
	}
}

export default {
	controller:Controller,
	template:template
};
