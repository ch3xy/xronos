import i18n from 'i18next';
import template from './menu.html';

class Controller {
	$onInit() {
		this.title = 'Xronos (alpha)';
	}

	t(key, params={}) {
	    return i18n.t('menu.' + key, params);
    }
}

export default {
	controller:Controller,
	template:template
};
