import moment from 'moment';

const template = `<h1>
    <a class="prev" ng-click="$ctrl.prev()">&lt;</a>
    <span class="title">{{$ctrl.title}}</span>
    <a class="next" ng-click="$ctrl.next()">&gt;</a>
</h1>`;

class Controller {
    constructor($state) {
        this.$state = $state;
    }

	$onInit() {
        this.title = this.date.format('MMMM YYYY');
	}

	prev() {
        this.navigate(this.date.subtract(1, 'months').startOf('month'));
    }

    next() {
        this.navigate(this.date.add(1, 'months').startOf('month'));
    }

    navigate(date) {
        this.$state.go('docs', {
            year:date.year(),
            month:date.month() + 1
        });
    }
}

export default {
	controller:Controller,
	template:template,
	bindings:{
		date:'<',
        onPrev:'&',
        onNext:'&'
	}
};
