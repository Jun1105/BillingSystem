import request from '@/utils/request'

function getlogin(){
    return request({
        url: '/hello',
        method: 'get'
    })
}

export {
    getlogin
}