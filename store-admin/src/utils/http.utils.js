import Vue from 'vue';
import NProgress from "nprogress";
import axios from 'axios';
import configDev from '@/config/config.dev.js';
import configProd from '@/config/config.prod.js';
import { TOKEN_KEY } from '@/utils/constant.js';
const IS_PROD = ['production', 'prod'].includes(process.env.NODE_ENV);



Vue.prototype.$progressInstance = 0;

let envConfig = configDev;

if (IS_PROD) {
    envConfig = configProd;
}


const get = (api, param = {}, options = {}) => {
    return request('get', api, param, null, options);
}

const post = (api, body, options = {}) => {
    return request('post', api, null, body, options);
}

const DELETE = (api, param = {}, options = {}) => {
    return request('delete', api, param, null, options);
}

const put = (api, body = {}, options = {}) => {
    return request('put', api, null, body, options);
}

const head = (api, body = {}, options = {}) => {
    return request('head', api, null, body, options);
}

const patch = (api, body = {}, options = {}) => {
    return request('head', api, null, body, options);
}

const request = (method, api, params, data, options) => {
    if (Vue.prototype.$progressInstance == 0) NProgress.start()
    Vue.prototype.$progressInstance += 1;

    let url = envConfig.base_url + api;
    options.headers = {
        ...(options.headers || {}),
        Authorization: localStorage.getItem(TOKEN_KEY) || ''
    }
    return new Promise(resolve => {
        axios({
            url,
            method,
            params,
            data,
            ...options
        }).then(res => {
            resolve(res.data);
        }).catch(err => {
            resolve({
                code: -1,
                msg: '网络请求失败'
            })
        }).finally(() => {
            Vue.prototype.$progressInstance -= 1;
            if (Vue.prototype.$progressInstance == 0) {
                NProgress.done();
            }
        })
    })


}

export default {
    get,
    post,
    delete: DELETE,
    put,
    head,
    patch,
    request
}