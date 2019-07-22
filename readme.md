*  何时使用 findFirst 和 findAny

    为什么会同时有findFirst和findAny呢？答案是并行。找到第一个元素在并行上限制更多。如果你不关心返回的元素是哪个，请使用 findAny ，因为它在使用并行流时限制较少。
*  读取资源文件

```
 try (final Stream<String> lines = Files.lines(Paths.get("src/main/resources","streamData/data.txt"), Charset.defaultCharset())){
            lines.forEach(System.out::println);
    } catch (IOException e) {
            e.printStackTrace();
        }
```
*   collector接口
```
public interface Collector<T, A, R> {
/**
supplier 方法必须返回一个结果为空的 Supplier ，也就是一个无参数函数,在调用时它会创建一个空的累加器实例，供数据收集过程使用
*/
Supplier<A> supplier();//新的结果容器
/**
accumulator 方法会返回执行归约操作的函数
*/
BiConsumer<A, T> accumulator();//将元素添加到结果容器
/**
在遍历完流后， finisher 方法必须返回在累积过程的最后要调用的一个函数，以便将累加器对象转换为整个集合操作的最终结果
*/
Function<A, R> finisher();//对结果容器应用最终转换
/**
 combiner 方法会返回一个供归约操作使用的函数，它定义了对流的各个子部分进行并行处理时，各个子部分归约所得的累加器要如何合并
 */
BinaryOperator<A> combiner();//合并两个结果容器,并行處理時用
/**
characteristics 会返回一个不可变的 Characteristics 集合，它定义了收集器的行为——尤其是关于流是否可以并行归约，以及可以使用哪些优化的提示
 Characteristics 是一个包含三个项目的枚举:
 UNORDERED ——归约结果不受流中项目的遍历和累积顺序的影响。
CONCURRENT —— accumulator 函数可以从多个线程同时调用，且该收集器可以并行归约流。如果收集器没有标为 UNORDERED ，那它仅在用于无序数据源时才可以并行归约。
IDENTITY_FINISH ——这表明完成器方法返回的函数是一个恒等函数，可以跳过。这种情况下，累加器对象将会直接用作归约过程的最终结果。这也意味着，将累加器 A 不加检
查地转换为结果 R 是安全的。

*/
Set<Characteristics> characteristics();//
}
```
T 是流中要收集的项目的泛型。  
A 是累加器的类型，累加器是在收集过程中用于累积部分结果的对象。  
R 是收集操作得到的对象（通常但并不一定是集合）的类型。  


顺序归约过程的逻辑步骤如下：
![顺序归约过程的逻辑步骤](/src/main/resources/collector/顺序归约过程的逻辑步骤.png  "顺序归约过程的逻辑步骤")

*  并行流用的线程是从哪儿来的？有多少个？怎么自定义这个过程呢？  

 并行流内部使用了默认的 ForkJoinPool，它默认的线 程 数 量 就是 你 的 处 理器 数 量 ， 这个 值 是 由 Runtime.getRuntime().available-
Processors() 得到的 .可 以 通 过 系 统 属 性 java.util.concurrent.ForkJoinPool.common.parallelism 来改变线程池大小，如下所示：
System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","12");
这是一个全局设置，它将影响代码中所有的并行流,除非你有很好的理由，否则我们强烈建议你不要修改它

