#### countArcHot 视频=硬币*0.4+收藏*0.3+弹幕*0.4+评论*0.4+播放*0.25+点赞*0.4+分享*0.6 最新视频（一天内发布）提权[总值*1.5]

```c
func countArcHot(t *api.Stat, ptime int64) int64 {
    if t == nil {
        return 0
    }
    hot := float64(t.Coin)*0.4 +
            float64(t.Fav)*0.3 +
            float64(t.Danmaku)*0.4 +
            float64(t.Reply)*0.4 +
            float64(t.View)*0.25 +
            float64(t.Like)*0.4 +
            float64(t.Share)*0.6
    if ptime >= time.Now().AddDate(0, 0, -1).Unix() && ptime <= time.Now().Unix() {
        hot *= 1.5
    }
    return int64(math.Floor(hot))
}
```

#### countArtHot 专栏=硬币*0.4+收藏*0.3+评论*0.4+阅读*0.25+点赞*0.4+分享*0.6 最新专栏（一天内发布）提权[总值*1.5]

```c
func countArtHot(t *model.Meta) int64 {
    if t.Stats == nil {
        return 0
    }
    hot := float64(t.Stats.Coin)*0.4 +
            float64(t.Stats.Favorite)*0.3 +
            float64(t.Stats.Reply)*0.4 +
            float64(t.Stats.View)*0.25 +
            float64(t.Stats.Like)*0.4 +
            float64(t.Stats.Share)*0.6
    if int64(t.PublishTime) >= time.Now().AddDate(0, 0, -1).Unix() && int64(t.PublishTime) <= time.Now().Unix() {
        hot *= 1.5
    }
    return int64(math.Floor(hot))
}
```

### 影响权重

- 视频： 硬币、收藏、弹幕、评论、播放量、点赞数、分享数、更新时间。
- 专栏：硬币、收藏、评论、阅读量、点赞数、分享数、更新时间。

> 收藏权重高，意味着重视内容质量，更重视大家觉得好的干货。
>
> 评论权重高，则意味着注重互动，更重视能吸引大家讨论的内容。
>
> 目前B站在代码上的思路来看，更重视能引发讨论热议的内容。



**如果你是UP主 :** 

那么想要提高自己视频/文章的曝光度，尽可能增加 **分享，评论，弹幕的数量** 会是更有效的方法，而去刷播放量则意义不大（这样就很好的解释了为什么有些UP老是骗弹幕 ~）

**如果你是用户 :**  

点赞、硬币、阅读量 可能更多的影响的是你所看UP的收益，想要为喜欢的UP增加曝光不如**多刷几条弹幕和评论**。

**如何搞定自己的首页推荐内容** ——— **对视频点击不感兴趣**能极大的减少相关话题的权重。

首页看到不感兴趣的视频，首页视频缩略图下面右边按钮点击，选择不感兴趣，一段时间就好了。