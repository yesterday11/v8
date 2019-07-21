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
