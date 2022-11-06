import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },

  {
    path: '/banner',
    component: Layout,
    alwaysShow: true,
    name: 'Banner',
    redirect: 'noredirect',
    meta: { title: '轮播图管理', icon: '轮播图' },
    children: [
      {
        path: 'banner_list',
        name: 'BannerList',
        component: () => import('@/views/banner/banner_list'),
        meta: { title: '首页轮播图', icon: '轮播图' }
      },
      {
        path: 'banner_form',
        name: 'BannerForm',
        hidden: true,
        component: () => import('@/views/banner/banner_form'),
        meta: { title: '首页轮播图操作', icon: '轮播图' }
      }
    ]
  },

  {
    path: '/section',
    component: Layout,
    alwaysShow: true,
    name: 'Section',
    redirect: 'noredirect',
    meta: { title: '版块管理', icon: '互动版块' },
    children: [
      {
        path: 'section_list',
        name: 'SectionList',
        component: () => import('@/views/section/section_list'),
        meta: { title: '版块管理', icon: '互动版块' }
      },
      {
        path: 'section_form',
        name: 'SectionForm',
        hidden: true,
        component: () => import('@/views/section/section_form'),
        meta: { title: '版块操作', icon: '互动版块' }
      }
    ]
  },

  {
    path: '/category',
    component: Layout,
    alwaysShow: true,
    name: 'Category',
    redirect: 'noredirect',
    meta: { title: '分类管理', icon: '分类' },
    children: [
      {
        path: 'category_list',
        name: 'CategoryList',
        component: () => import('@/views/category/category_list'),
        meta: { title: '分类管理', icon: '分类' }
      },
      {
        path: 'category_form',
        name: 'CategoryForm',
        hidden: true,
        component: () => import('@/views/category/category_form'),
        meta: { title: '分类操作', icon: '分类' }
      }
    ]
  },

  {
    path: '/article',
    component: Layout,
    alwaysShow: true,
    name: 'Article',
    redirect: 'noredirect',
    meta: { title: '文章管理', icon: '文章' },
    children: [
      {
        path: 'article_list',
        name: 'ArticleList',
        component: () => import('@/views/article/article_list'),
        meta: { title: '文章管理', icon: '文章' }
      },
      {
        path: 'article_form',
        name: 'ArticleForm',
        hidden: true,
        component: () => import('@/views/article/article_form'),
        meta: { title: '文章操作', icon: '文章' }
      }
    ]
  },

  {
    path: '/blogroll',
    component: Layout,
    alwaysShow: true,
    name: 'Blogroll',
    redirect: 'noredirect',
    meta: { title: '友情链接管理', icon: '友情链接' },
    children: [
      {
        path: 'blogroll_list',
        name: 'BlogrollList',
        component: () => import('@/views/blogroll/blogroll_list'),
        meta: { title: '友情链接管理', icon: '友情链接' }
      },
      {
        path: 'blogroll_form',
        name: 'BlogrollForm',
        hidden: true,
        component: () => import('@/views/blogroll/blogroll_form'),
        meta: { title: '友情链接管理操作', icon: '友情链接' }
      }
    ]
  },

  {
    path: '/config',
    component: Layout,
    alwaysShow: true,
    name: 'Config',
    redirect: 'noredirect',
    meta: { title: '配置管理', icon: '配置' },
    children: [
      {
        path: 'system_config',
        name: 'SystemConfig',
        component: () => import('@/views/config/system_config'),
        meta: { title: '系统配置', icon: '配置' }
      },
      {
        path: 'oss_config',
        name: 'OssConfig',
        component: () => import('@/views/config/oss_config'),
        meta: { title: 'OSS配置', icon: '配置' }
      },
      {
        path: 'rss_config',
        name: 'RssConfig',
        component: () => import('@/views/config/rss_config'),
        meta: { title: 'RSS配置', icon: '配置' }
      }
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
