import request from '@/utils/request'

export function listBrandRelation(brandId) {
    return request({
        url: '/product/brandRelation/listById/' + brandId,
        method: 'get',
    })
}

export function addBrandRelation(data) {
    return request({
        url: '/product/brandRelation',
        method: 'post',
        data
    })
}

export function deleteBrandRelation(id) {
    return request({
        url: '/product/brandRelation' + id,
        method: 'delete',
        data
    })
}