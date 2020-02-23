package per.goweii.wanandroid.utils

import android.content.Context
import per.goweii.wanandroid.module.main.activity.ArticleActivity
import per.goweii.wanandroid.module.main.activity.WebActivity
import per.goweii.wanandroid.module.main.model.ArticleBean

/**
 * @author CuiZhen
 * @date 2020/2/23
 */
class UrlOpenUtils(
        private val url: String
) {
    private var title: String = ""

    private var articleId: Int = 0
    private var author: String = ""
    private var collected: Boolean = false

    companion object {
        fun with(url: String?) = UrlOpenUtils(url ?: "")

        fun with(article: ArticleBean?) = with(article?.link).apply {
            article?.let {
                articleId(if (it.originId != 0) it.originId else it.id)
                title(it.title)
                collected(it.isCollect)
            }
        }
    }

    fun title(title: String) = apply {
        this.title = title
    }

    fun articleId(articleId: Int) = apply {
        this.articleId = articleId
    }

    fun author(author: String) = apply {
        this.author = author
    }

    fun collected(collected: Boolean) = apply {
        this.collected = collected
    }

    fun open(context: Context?) {
        context ?: return
        if (articleId > 0) {
            ArticleActivity.start(context, url, title, articleId, collected)
        } else {
            WebActivity.start(context, url, title, 0, collected)
        }
    }
}