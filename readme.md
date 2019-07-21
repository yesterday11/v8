*  何时使用 findFirst 和 findAny

    为什么会同时有 findFirst 和 findAny 呢？答案是并行。找到第一个元素在并行上限制更多。如果你不关心返回的元素是哪个，请使用 findAny ，因为它在使用并行流时限制较少。
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
BinaryOperator<A> combiner();
Set<Characteristics> characteristics();
}
```
T 是流中要收集的项目的泛型。  
A 是累加器的类型，累加器是在收集过程中用于累积部分结果的对象。  
R 是收集操作得到的对象（通常但并不一定是集合）的类型。  



![顺序归约过程的逻辑步骤](/src/main/resources/collector/顺序归约过程的逻辑步骤.png  "顺序归约过程的逻辑步骤")
