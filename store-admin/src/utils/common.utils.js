export const concatUrlParam = (url, data) => {
    if (!data || typeof data !== 'object') {
        return url
    }
    for (let key in data) {
        let concatStr = url.indexOf('?') < 0 ? '?' : '&';
        url += concatStr + key + '=' + data[key];
    }
    return url
}

