import request from '@/utils/request'

function getlogin(){
    return request({
        url: '/login',
        method: 'post'
    })
}

export {
    getlogin
}