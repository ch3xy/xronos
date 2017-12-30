import moment from "moment";

export default () => {
    return (duration, alwaysPrefixed) => {
        if(!moment.isDuration(duration)) {
            return duration;
        }

        const milliseconds = duration.asMilliseconds();

        if(isNaN(milliseconds)) {
            return '--:--';
        }

        const prefix = milliseconds < 0 ? '-' : (alwaysPrefixed ? '+' : '');
        return prefix + [
            ('00' + Math.abs(parseInt(duration.asHours()))).slice(-2),
            ('00' + Math.abs(duration.minutes())).slice(-2)
        ].join(':');
    };
};
