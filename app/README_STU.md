
一:自定义View

1.自定义属性

2.自定义View

  onDraw()
  onMesure()

  MATCH_PARNET

  WRAP_CONTENT

  MeasureSpe========

    EXACTLY：一般是设置了明确的值或者是MATCH_PARENT

    AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT

    UNSPECIFIED：表示子布局想要多大就多大，很少使用


3. 命名空间

4. 自定义View 的步骤

    1. 自定义属性
    2. 在构造方法中获取对应的属性
    3. 覆写 onDraw()方法
    4. 覆写 onMeasure()方法

二. 自定义ViewGroup

1、ViewGroup的职责是啥？

，ViewGroup的职能为：给childView计算出建议的宽和高和测量模式 ；决定childView的位置；为什么只是建议的宽和高，
而不是直接确定呢，别忘了childView宽和高可以设置为wrap_content，这样只有childView才能计算出自己的宽和高。

2、View的职责是啥？

View的职责，根据测量模式和ViewGroup给出的建议的宽和高，计算出自己的宽和高；同时还有个更重要的职责是：
在ViewGroup为其指定的区域内绘制自己的形态。


3、ViewGroup和LayoutParams之间的关系？

大家可以回忆一下，当在LinearLayout中写childView的时候，可以写layout_gravity，layout_weight属性；在
RelativeLayout中的childView有layout_centerInParent属性，却没有layout_gravity，layout_weight，
这是为什么呢？这是因为每个ViewGroup需要指定一个LayoutParams，用于确定支持childView支持哪些属性，
比如LinearLayout指定LinearLayout.LayoutParams等。如果大家去看LinearLayout的源码，会发现其内部定义了
LinearLayout.LayoutParams，在此类中，你可以发现weight和gravity的身影。


4. ViewGroup 为childView 提供的3中精确的 测量方式

 EXACTLY: 表示设置了精确的值 在childView 为精确值 或者match_parent的时候

 AT_MOST: 表示最大的限制是  在chileView 设置为watch_parent 的时候

 UNSEPICIED: 表示子布局想要多大就有多大


5. 关于 AttributeSet
 构造方法中的有个参数叫做AttributeSet（eg: MyTextView(Context context, AttributeSet attrs) ）这个参数看
 名字就知道包含的是参数的集合，那么我能不能通过它去获取我的自定义属性呢？









