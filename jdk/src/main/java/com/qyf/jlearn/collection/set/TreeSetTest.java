package com.qyf.jlearn.collection.set;

/**
 * 类描述：
 * <p>
 * 与 TreeMap 相似。是对 TreeMap 的封装。
 * <p>
 * Set接口的实现类，也拥有set接口的一般特性，但是不同的是他也实现了SortSet接口，它底层采用的是红黑树算法（红黑树就是满足一下红黑性质的二叉搜索树：①每个节点是黑色或者红色②根节点是黑色的③每个叶子结点是黑色的④如果一个节点是红色的，那么他的两个子节点是黑色的⑤对每个节点，从该节点到其所有的后代叶子结点的简单路径上，仅包含相同数目的黑色结点，红黑树是许多“平衡”搜索树的一种，可以保证在最坏情况下的基本操作集合的时间复杂度为O(lgn)。普及：二叉搜索树的性质：它或者是一棵空树；或者是具有下列性质的二叉树：若左子树不空，则左子树上所有结点的值均小于它的根结点的值；若右子树不空，则右子树上所有结点的值均大于它的根结点的值；左、右子树也分别为二叉排序树。若子树为空，查找不成功。），要注意的是在TreeSet集合中只能存储相同类型对象的引用。
 * Tree最重要的就是它的两种排序方式：自然排序和客户端排序
 * <p>
 * 自然排序；实现了Comparable接口，所以TreeSet可以调用对象的ComparableTo()方法来比较集合的大小，然后进行升序排序，这种排序方式叫做自然排序。其中实现了Comparable接口的还有BigDecimal、BigInteger、Byte、Double、Float、Integer、Long、Short（按照数字大小排序）、Character（按照Unicode值的数字大小进行排序）String（按照字符串中字符的Unicode值进行排序）类等。
 * 客户化排序：其实就是实现java.util.Comparator<Type>接口提供的具体的排序方式，<Type> 是具体要比较对象的类型，他有个compare的方法，如compare(x,y)返回值大于0表示x大于y，以此类推，当我们希望按照自己的想法排序的时候可以重写compare方法。
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/27 16:28
 */
public class TreeSetTest {
}
