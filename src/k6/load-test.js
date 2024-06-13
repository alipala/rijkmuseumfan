import http from 'k6/http';
import { check, sleep } from 'k6';
import { Trend } from 'k6/metrics';

const BASE_URL = 'https://www.rijksmuseum.nl/api/en/collection';

export let options = {
    stages: [
        // Average load test
        { duration: '1m', target: 50 }, // Ramp up to 50 users over 2 minutes
        { duration: '3m', target: 50 }, // Stay at 50 users for 3 minutes
        { duration: '2m', target: 0 },  // Ramp down to 0 users over 2 minutes

        // Stress test
        { duration: '2m', target: 100 }, // Ramp up to 100 users over 2 minutes
        { duration: '3m', target: 100 }, // Stay at 100 users for 3 minutes
        { duration: '2m', target: 0 },   // Ramp down to 0 users over 2 minutes

        // Spike test
        { duration: '1m', target: 200 }, // Spike up to 200 users over 1 minute
        { duration: '1m', target: 200 }, // Stay at 200 users for 1 minute
        { duration: '1m', target: 0 },   // Ramp down to 0 users over 1 minute
    ],
};

const myTrend = new Trend('response_time');

export default function () {
    const apiKey = 'jw4VW7dk';
    const res = http.get(`${BASE_URL}?key=${apiKey}&q=rembrandt`);

    check(res, {
        'status was 200': (r) => r.status === 200,
        'response time was less than 200ms': (r) => r.timings.duration < 200,
    });

    myTrend.add(res.timings.duration);

    sleep(1);
}
