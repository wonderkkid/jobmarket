
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import JobManager from "./components/JobManager"

import WorkingManager from "./components/WorkingManager"

import PaymentManager from "./components/PaymentManager"


import JobInfo from "./components/JobInfo"
export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/jobs',
                name: 'JobManager',
                component: JobManager
            },

            {
                path: '/workings',
                name: 'WorkingManager',
                component: WorkingManager
            },

            {
                path: '/payments',
                name: 'PaymentManager',
                component: PaymentManager
            },


            {
                path: '/jobInfos',
                name: 'JobInfo',
                component: JobInfo
            },


    ]
})
