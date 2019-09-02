import request from '@/request'


export function getArticles(query, page) {
  return request({
    url: '/articles',
    method: 'get',
    params: {
      pageNumber: page.pageNumber,
      pageSize: page.pageSize,
      name: page.name,
      sort: page.sort,
      year: query.year,
      month: query.month,
      tagId: query.tagId,
      categoryId: query.categoryId
    }
  })
}

export function getArticlesMoyu(query,page) {
  return request({
    url:`/articles/moyu`,
    method:'get',
    params: {
      pageNumber: page.pageNumber,
      pageSize: page.pageSize,
      name: page.name,
      sort: page.sort,
      year: query.year,
      month: query.month,
      tagId: query.tagId,
      categoryId: query.categoryId
    }
  })
}

export function deleteArticle(id) {
  return request({
    url:`/articles/delete/${id}`,
    method:'get',
  })
}


export function getHotArticles() {
  return request({
    url: '/articles/hot',
    method: 'get',
  })
}

export function getNewArticles() {
  return request({
    url: '/articles/new',
    method: 'get'
  })
}

export function viewArticle(id) {
  return request({
    url: `/articles/view/${id}`,
    method: 'get'
  })
}

/**
 * 用于获取不同板块的文章
 * @param id
 */
export function getArticlesByCategory(id) {
  return request({
    url: `/articles/category/${id}`,
    method: 'get'
  })
}



export function getArticlesOJ() {
  return getArticlesByCategory(2);
}

export function getArticlesCampus() {
  return getArticlesByCategory(3);
}

export function getArticlesLife() {
  return getArticlesByCategory(4);
}

export function getArticlesByTag(id) {
  return request({
    url: `/articles/tag/${id}`,
    method: 'get'
  })
}




export function publishArticle(article) {
  return request({
    url: '/articles/publish',
    method: 'post',
    data: article
  })
}

export function listArchives() {
  return request({
    url: '/articles/listArchives',
    method: 'get'
  })
}

export function getArticleById(id) {
  return request({
    url: `/articles/${id}`,
    method: 'get'
  })
}
