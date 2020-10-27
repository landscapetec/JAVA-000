### 第二周：第 3 课作业实践
##### <font color=red>第 1 题</font>：使用 GCLogAnalysis.java 自己演练一遍串行/并行/CMS/G1的案例。
- 串型GC
```
java -server -Xms512m -Xmx512m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps GCLogAnalysis
正在执行...
2020-10-26T14:31:24.545-0800: 0.268: [GC (Allocation Failure) 2020-10-26T14:31:24.545-0800: 0.268: [DefNew: 139776K->17471K(157248K), 0.0329522 secs] 139776K->40624K(506816K), 0.0330220 secs] [Times: user=0.02 sys=0.01, real=0.03 secs]
2020-10-26T14:31:24.611-0800: 0.334: [GC (Allocation Failure) 2020-10-26T14:31:24.611-0800: 0.334: [DefNew: 157247K->17469K(157248K), 0.0572569 secs] 180400K->86731K(506816K), 0.0573249 secs] [Times: user=0.03 sys=0.03, real=0.05 secs]
2020-10-26T14:31:24.703-0800: 0.426: [GC (Allocation Failure) 2020-10-26T14:31:24.703-0800: 0.426: [DefNew: 157245K->17471K(157248K), 0.0804290 secs] 226507K->130933K(506816K), 0.0804829 secs] [Times: user=0.02 sys=0.02, real=0.08 secs]
2020-10-26T14:31:24.821-0800: 0.544: [GC (Allocation Failure) 2020-10-26T14:31:24.821-0800: 0.544: [DefNew: 157138K->17470K(157248K), 0.1096306 secs] 270600K->172384K(506816K), 0.1096935 secs] [Times: user=0.02 sys=0.02, real=0.11 secs]
2020-10-26T14:31:24.973-0800: 0.696: [GC (Allocation Failure) 2020-10-26T14:31:24.973-0800: 0.696: [DefNew: 157246K->17471K(157248K), 0.0773885 secs] 312160K->210977K(506816K), 0.0774509 secs] [Times: user=0.02 sys=0.02, real=0.08 secs]
2020-10-26T14:31:25.090-0800: 0.813: [GC (Allocation Failure) 2020-10-26T14:31:25.090-0800: 0.813: [DefNew: 157247K->17471K(157248K), 0.0513113 secs] 350753K->254829K(506816K), 0.0513800 secs] [Times: user=0.02 sys=0.02, real=0.05 secs]
2020-10-26T14:31:25.179-0800: 0.902: [GC (Allocation Failure) 2020-10-26T14:31:25.179-0800: 0.902: [DefNew: 157247K->17472K(157248K), 0.0639092 secs] 394605K->293540K(506816K), 0.0639821 secs] [Times: user=0.03 sys=0.02, real=0.07 secs]
2020-10-26T14:31:25.278-0800: 1.001: [GC (Allocation Failure) 2020-10-26T14:31:25.278-0800: 1.001: [DefNew: 157239K->17471K(157248K), 0.0933989 secs] 433307K->338665K(506816K), 0.0934634 secs] [Times: user=0.02 sys=0.02, real=0.10 secs]
2020-10-26T14:31:25.405-0800: 1.128: [GC (Allocation Failure) 2020-10-26T14:31:25.405-0800: 1.128: [DefNew: 157247K->157247K(157248K), 0.0000285 secs]2020-10-26T14:31:25.405-0800: 1.128: [Tenured: 321193K->272080K(349568K), 0.0716096 secs] 478441K->272080K(506816K), [Metaspace: 2688K->2688K(1056768K)], 0.0717293 secs] [Times: user=0.07 sys=0.00, real=0.07 secs]
执行结束!共生成对象次数:4664
Heap
 def new generation   total 157248K, used 5677K [0x00000007a0000000, 0x00000007aaaa0000, 0x00000007aaaa0000)
  eden space 139776K,   4% used [0x00000007a0000000, 0x00000007a058b6b0, 0x00000007a8880000)
  from space 17472K,   0% used [0x00000007a8880000, 0x00000007a8880000, 0x00000007a9990000)
  to   space 17472K,   0% used [0x00000007a9990000, 0x00000007a9990000, 0x00000007aaaa0000)
 tenured generation   total 349568K, used 272080K [0x00000007aaaa0000, 0x00000007c0000000, 0x00000007c0000000)
   the space 349568K,  77% used [0x00000007aaaa0000, 0x00000007bb454008, 0x00000007bb454200, 0x00000007c0000000)
 Metaspace       used 2695K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K
```
- 并行GC
```
java -server -Xms512m -Xmx512m -XX:+UseParallelGC -XX:+UseParallelOldGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps GCLogAnalysis
正在执行...
2020-10-26T14:39:57.165-0800: 0.217: [GC (Allocation Failure) [PSYoungGen: 131523K->21503K(153088K)] 131523K->49331K(502784K), 0.0265017 secs] [Times: user=0.03 sys=0.06, real=0.03 secs]
2020-10-26T14:39:57.225-0800: 0.277: [GC (Allocation Failure) [PSYoungGen: 153087K->21490K(153088K)] 180915K->92551K(502784K), 0.0368993 secs] [Times: user=0.03 sys=0.07, real=0.04 secs]
2020-10-26T14:39:57.291-0800: 0.344: [GC (Allocation Failure) [PSYoungGen: 153074K->21501K(153088K)] 224135K->133882K(502784K), 0.0275769 secs] [Times: user=0.04 sys=0.05, real=0.02 secs]
2020-10-26T14:39:57.345-0800: 0.398: [GC (Allocation Failure) [PSYoungGen: 153085K->21499K(153088K)] 265466K->181398K(502784K), 0.0333042 secs] [Times: user=0.04 sys=0.06, real=0.03 secs]
2020-10-26T14:39:57.408-0800: 0.460: [GC (Allocation Failure) [PSYoungGen: 153083K->21495K(153088K)] 312982K->221858K(502784K), 0.0289015 secs] [Times: user=0.03 sys=0.05, real=0.03 secs]
2020-10-26T14:39:57.464-0800: 0.517: [GC (Allocation Failure) [PSYoungGen: 153079K->21495K(80384K)] 353442K->271896K(430080K), 0.0347614 secs] [Times: user=0.04 sys=0.06, real=0.03 secs]
2020-10-26T14:39:57.510-0800: 0.562: [GC (Allocation Failure) [PSYoungGen: 80055K->38561K(116736K)] 330456K->293845K(466432K), 0.0106156 secs] [Times: user=0.02 sys=0.01, real=0.01 secs]
2020-10-26T14:39:57.535-0800: 0.588: [GC (Allocation Failure) [PSYoungGen: 97181K->54227K(116736K)] 352465K->314009K(466432K), 0.0156303 secs] [Times: user=0.03 sys=0.00, real=0.02 secs]
2020-10-26T14:39:57.565-0800: 0.617: [GC (Allocation Failure) [PSYoungGen: 113107K->57841K(116736K)] 372889K->330195K(466432K), 0.0233726 secs] [Times: user=0.04 sys=0.02, real=0.02 secs]
2020-10-26T14:39:57.605-0800: 0.657: [GC (Allocation Failure) [PSYoungGen: 116721K->35133K(116736K)] 389075K->342105K(466432K), 0.0282104 secs] [Times: user=0.04 sys=0.03, real=0.03 secs]
2020-10-26T14:39:57.633-0800: 0.685: [Full GC (Ergonomics) [PSYoungGen: 35133K->0K(116736K)] [ParOldGen: 306972K->244560K(349696K)] 342105K->244560K(466432K), [Metaspace: 2688K->2688K(1056768K)], 0.0536814 secs] [Times: user=0.11 sys=0.01, real=0.05 secs]
2020-10-26T14:39:57.699-0800: 0.751: [GC (Allocation Failure) [PSYoungGen: 58729K->19314K(116736K)] 303290K->263875K(466432K), 0.0058665 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
2020-10-26T14:39:57.720-0800: 0.772: [GC (Allocation Failure) [PSYoungGen: 78179K->23600K(116736K)] 322740K->286029K(466432K), 0.0092506 secs] [Times: user=0.03 sys=0.00, real=0.00 secs]
2020-10-26T14:39:57.748-0800: 0.800: [GC (Allocation Failure) [PSYoungGen: 82480K->17416K(116736K)] 344909K->300352K(466432K), 0.0077288 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
2020-10-26T14:39:57.768-0800: 0.820: [GC (Allocation Failure) [PSYoungGen: 76296K->22203K(116736K)] 359232K->320873K(466432K), 0.0088919 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
2020-10-26T14:39:57.777-0800: 0.829: [Full GC (Ergonomics) [PSYoungGen: 22203K->0K(116736K)] [ParOldGen: 298670K->266628K(349696K)] 320873K->266628K(466432K), [Metaspace: 2688K->2688K(1056768K)], 0.0569595 secs] [Times: user=0.11 sys=0.00, real=0.06 secs]
2020-10-26T14:39:57.849-0800: 0.901: [GC (Allocation Failure) [PSYoungGen: 58561K->19311K(116736K)] 325189K->285940K(466432K), 0.0058657 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
2020-10-26T14:39:57.870-0800: 0.922: [GC (Allocation Failure) [PSYoungGen: 78089K->22461K(116736K)] 344717K->306575K(466432K), 0.0100240 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
2020-10-26T14:39:57.893-0800: 0.946: [GC (Allocation Failure) [PSYoungGen: 81006K->21845K(116736K)] 365119K->327809K(466432K), 0.0130129 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
2020-10-26T14:39:57.906-0800: 0.959: [Full GC (Ergonomics) [PSYoungGen: 21845K->0K(116736K)] [ParOldGen: 305963K->288090K(349696K)] 327809K->288090K(466432K), [Metaspace: 2688K->2688K(1056768K)], 0.0608154 secs] [Times: user=0.13 sys=0.01, real=0.06 secs]
2020-10-26T14:39:57.979-0800: 1.031: [GC (Allocation Failure) [PSYoungGen: 58880K->17870K(116736K)] 346970K->305961K(466432K), 0.0047535 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
2020-10-26T14:39:57.998-0800: 1.051: [GC (Allocation Failure) [PSYoungGen: 76358K->23548K(116736K)] 364449K->328884K(466432K), 0.0089537 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
2020-10-26T14:39:58.021-0800: 1.074: [GC (Allocation Failure) [PSYoungGen: 82428K->21611K(116736K)] 387764K->349446K(466432K), 0.0193153 secs] [Times: user=0.03 sys=0.01, real=0.02 secs]
2020-10-26T14:39:58.041-0800: 1.093: [Full GC (Ergonomics) [PSYoungGen: 21611K->0K(116736K)] [ParOldGen: 327834K->302147K(349696K)] 349446K->302147K(466432K), [Metaspace: 2688K->2688K(1056768K)], 0.0598599 secs] [Times: user=0.13 sys=0.01, real=0.06 secs]
执行结束!共生成对象次数:6024
Heap
 PSYoungGen      total 116736K, used 2505K [0x00000007b5580000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 58880K, 4% used [0x00000007b5580000,0x00000007b57f2728,0x00000007b8f00000)
  from space 57856K, 0% used [0x00000007bc780000,0x00000007bc780000,0x00000007c0000000)
  to   space 57856K, 0% used [0x00000007b8f00000,0x00000007b8f00000,0x00000007bc780000)
 ParOldGen       total 349696K, used 302147K [0x00000007a0000000, 0x00000007b5580000, 0x00000007b5580000)
  object space 349696K, 86% used [0x00000007a0000000,0x00000007b2710c58,0x00000007b5580000)
 Metaspace       used 2695K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K
```
-CMS GC
```
java -server -Xms512m -Xmx512m  -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps GCLogAnalysis
正在执行...
2020-10-26T14:42:09.415-0800: 0.224: [GC (Allocation Failure) 2020-10-26T14:42:09.415-0800: 0.224: [ParNew: 139776K->17471K(157248K), 0.0309614 secs] 139776K->45045K(506816K), 0.0310469 secs] [Times: user=0.03 sys=0.04, real=0.03 secs]
2020-10-26T14:42:09.485-0800: 0.295: [GC (Allocation Failure) 2020-10-26T14:42:09.485-0800: 0.295: [ParNew: 157014K->17472K(157248K), 0.0320538 secs] 184588K->85578K(506816K), 0.0321183 secs] [Times: user=0.04 sys=0.05, real=0.03 secs]
2020-10-26T14:42:09.547-0800: 0.357: [GC (Allocation Failure) 2020-10-26T14:42:09.548-0800: 0.357: [ParNew: 157248K->17472K(157248K), 0.0392165 secs] 225354K->126907K(506816K), 0.0392901 secs] [Times: user=0.11 sys=0.02, real=0.04 secs]
2020-10-26T14:42:09.620-0800: 0.429: [GC (Allocation Failure) 2020-10-26T14:42:09.620-0800: 0.429: [ParNew: 157248K->17472K(157248K), 0.0386102 secs] 266683K->167434K(506816K), 0.0386751 secs] [Times: user=0.08 sys=0.03, real=0.03 secs]
2020-10-26T14:42:09.688-0800: 0.497: [GC (Allocation Failure) 2020-10-26T14:42:09.688-0800: 0.497: [ParNew: 157026K->17471K(157248K), 0.0434386 secs] 306988K->213458K(506816K), 0.0435023 secs] [Times: user=0.12 sys=0.03, real=0.05 secs]
2020-10-26T14:42:09.731-0800: 0.541: [GC (CMS Initial Mark) [1 CMS-initial-mark: 195986K(349568K)] 213899K(506816K), 0.0003546 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-26T14:42:09.732-0800: 0.542: [CMS-concurrent-mark-start]
2020-10-26T14:42:09.739-0800: 0.548: [CMS-concurrent-mark: 0.007/0.007 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
2020-10-26T14:42:09.739-0800: 0.548: [CMS-concurrent-preclean-start]
2020-10-26T14:42:09.739-0800: 0.549: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-26T14:42:09.739-0800: 0.549: [CMS-concurrent-abortable-preclean-start]
2020-10-26T14:42:09.762-0800: 0.572: [GC (Allocation Failure) 2020-10-26T14:42:09.762-0800: 0.572: [ParNew: 157247K->17469K(157248K), 0.0401326 secs] 353234K->256036K(506816K), 0.0401974 secs] [Times: user=0.12 sys=0.02, real=0.04 secs]
2020-10-26T14:42:09.834-0800: 0.644: [GC (Allocation Failure) 2020-10-26T14:42:09.834-0800: 0.644: [ParNew: 157245K->17472K(157248K), 0.0377049 secs] 395812K->297231K(506816K), 0.0377754 secs] [Times: user=0.10 sys=0.03, real=0.04 secs]
2020-10-26T14:42:09.902-0800: 0.712: [GC (Allocation Failure) 2020-10-26T14:42:09.902-0800: 0.712: [ParNew: 157248K->17471K(157248K), 0.0454726 secs] 437007K->339863K(506816K), 0.0455517 secs] [Times: user=0.09 sys=0.02, real=0.04 secs]
2020-10-26T14:42:09.982-0800: 0.792: [GC (Allocation Failure) 2020-10-26T14:42:09.982-0800: 0.792: [ParNew: 157247K->157247K(157248K), 0.0000493 secs]2020-10-26T14:42:09.982-0800: 0.792: [CMS2020-10-26T14:42:09.982-0800: 0.792: [CMS-concurrent-abortable-preclean: 0.009/0.242 secs] [Times: user=0.42 sys=0.07, real=0.25 secs]
 (concurrent mode failure): 322391K->254600K(349568K), 0.0724807 secs] 479639K->254600K(506816K), [Metaspace: 2689K->2689K(1056768K)], 0.0726573 secs] [Times: user=0.07 sys=0.00, real=0.07 secs]
2020-10-26T14:42:10.086-0800: 0.895: [GC (Allocation Failure) 2020-10-26T14:42:10.086-0800: 0.895: [ParNew: 139756K->17471K(157248K), 0.0106907 secs] 394357K->305654K(506816K), 0.0107724 secs] [Times: user=0.04 sys=0.00, real=0.01 secs]
2020-10-26T14:42:10.097-0800: 0.906: [GC (CMS Initial Mark) [1 CMS-initial-mark: 288182K(349568K)] 305748K(506816K), 0.0002046 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-26T14:42:10.097-0800: 0.907: [CMS-concurrent-mark-start]
2020-10-26T14:42:10.100-0800: 0.910: [CMS-concurrent-mark: 0.004/0.004 secs] [Times: user=0.00 sys=0.01, real=0.01 secs]
2020-10-26T14:42:10.101-0800: 0.910: [CMS-concurrent-preclean-start]
2020-10-26T14:42:10.102-0800: 0.911: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-26T14:42:10.102-0800: 0.911: [CMS-concurrent-abortable-preclean-start]
2020-10-26T14:42:10.128-0800: 0.938: [GC (Allocation Failure) 2020-10-26T14:42:10.128-0800: 0.938: [ParNew: 157247K->17472K(157248K), 0.0245866 secs] 445430K->348863K(506816K), 0.0246638 secs] [Times: user=0.05 sys=0.00, real=0.03 secs]
2020-10-26T14:42:10.154-0800: 0.964: [CMS-concurrent-abortable-preclean: 0.002/0.052 secs] [Times: user=0.08 sys=0.00, real=0.05 secs]
2020-10-26T14:42:10.154-0800: 0.964: [GC (CMS Final Remark) [YG occupancy: 23761 K (157248 K)]2020-10-26T14:42:10.154-0800: 0.964: [Rescan (parallel) , 0.0021022 secs]2020-10-26T14:42:10.157-0800: 0.966: [weak refs processing, 0.0000324 secs]2020-10-26T14:42:10.157-0800: 0.966: [class unloading, 0.0003794 secs]2020-10-26T14:42:10.157-0800: 0.967: [scrub symbol table, 0.0007226 secs]2020-10-26T14:42:10.158-0800: 0.968: [scrub string table, 0.0003578 secs][1 CMS-remark: 331391K(349568K)] 355152K(506816K), 0.0037635 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-26T14:42:10.158-0800: 0.968: [CMS-concurrent-sweep-start]
2020-10-26T14:42:10.161-0800: 0.971: [CMS-concurrent-sweep: 0.002/0.002 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
2020-10-26T14:42:10.161-0800: 0.971: [CMS-concurrent-reset-start]
2020-10-26T14:42:10.162-0800: 0.972: [CMS-concurrent-reset: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-26T14:42:10.192-0800: 1.001: [GC (Allocation Failure) 2020-10-26T14:42:10.192-0800: 1.001: [ParNew: 156794K->156794K(157248K), 0.0000671 secs]2020-10-26T14:42:10.192-0800: 1.002: [CMS: 303806K->294296K(349568K), 0.0647455 secs] 460600K->294296K(506816K), [Metaspace: 2689K->2689K(1056768K)], 0.0649235 secs] [Times: user=0.06 sys=0.00, real=0.06 secs]
2020-10-26T14:42:10.257-0800: 1.067: [GC (CMS Initial Mark) [1 CMS-initial-mark: 294296K(349568K)] 297609K(506816K), 0.0002668 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-26T14:42:10.257-0800: 1.067: [CMS-concurrent-mark-start]
2020-10-26T14:42:10.264-0800: 1.073: [CMS-concurrent-mark: 0.006/0.006 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
2020-10-26T14:42:10.264-0800: 1.073: [CMS-concurrent-preclean-start]
2020-10-26T14:42:10.264-0800: 1.074: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-26T14:42:10.264-0800: 1.074: [CMS-concurrent-abortable-preclean-start]
执行结束!共生成对象次数:6865
Heap
 par new generation   total 157248K, used 135693K [0x00000007a0000000, 0x00000007aaaa0000, 0x00000007aaaa0000)
  eden space 139776K,  97% used [0x00000007a0000000, 0x00000007a84835b0, 0x00000007a8880000)
  from space 17472K,   0% used [0x00000007a8880000, 0x00000007a8880000, 0x00000007a9990000)
  to   space 17472K,   0% used [0x00000007a9990000, 0x00000007a9990000, 0x00000007aaaa0000)
 concurrent mark-sweep generation total 349568K, used 294296K [0x00000007aaaa0000, 0x00000007c0000000, 0x00000007c0000000)
 Metaspace       used 2695K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K
```
- Garbage First(G1)
```
java -server -Xms512m -Xmx512m  -XX:+UseG1GC -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps GCLogAnalysis
正在执行...
2020-10-26T14:43:46.624-0800: 0.155: [GC pause (G1 Evacuation Pause) (young) 32M->7223K(512M), 0.0044431 secs]
2020-10-26T14:43:46.640-0800: 0.171: [GC pause (G1 Evacuation Pause) (young) 34M->15M(512M), 0.0066238 secs]
2020-10-26T14:43:46.670-0800: 0.202: [GC pause (G1 Evacuation Pause) (young) 49M->27M(512M), 0.0068685 secs]
2020-10-26T14:43:46.938-0800: 0.469: [GC pause (G1 Evacuation Pause) (young)-- 414M->291M(512M), 0.0253566 secs]
2020-10-26T14:43:46.964-0800: 0.495: [GC pause (G1 Evacuation Pause) (young) (initial-mark) 292M->291M(512M), 0.0140522 secs]
2020-10-26T14:43:46.978-0800: 0.509: [GC concurrent-root-region-scan-start]
2020-10-26T14:43:46.978-0800: 0.510: [GC concurrent-root-region-scan-end, 0.0002512 secs]
2020-10-26T14:43:46.978-0800: 0.510: [GC concurrent-mark-start]
2020-10-26T14:43:46.984-0800: 0.518: [GC concurrent-mark-end, 0.0061072 secs]
2020-10-26T14:43:46.988-0800: 0.519: [GC remark, 0.0021115 secs]
2020-10-26T14:43:46.990-0800: 0.522: [GC cleanup 310M->310M(512M), 0.0015902 secs]
2020-10-26T14:43:47.016-0800: 0.547: [GC pause (G1 Evacuation Pause) (young) 379M->322M(512M), 0.0047373 secs]
2020-10-26T14:43:47.025-0800: 0.556: [GC pause (G1 Evacuation Pause) (mixed) 339M->279M(512M), 0.0045101 secs]
2020-10-26T14:43:47.038-0800: 0.569: [GC pause (G1 Evacuation Pause) (mixed) 308M->243M(512M), 0.0057143 secs]
2020-10-26T14:43:47.049-0800: 0.580: [GC pause (G1 Evacuation Pause) (mixed) 267M->217M(512M), 0.0091979 secs]
2020-10-26T14:43:47.059-0800: 0.590: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 219M->218M(512M), 0.0022216 secs]
2020-10-26T14:43:47.061-0800: 0.593: [GC concurrent-root-region-scan-start]
2020-10-26T14:43:47.062-0800: 0.593: [GC concurrent-root-region-scan-end, 0.0005113 secs]
2020-10-26T14:43:47.062-0800: 0.593: [GC concurrent-mark-start]
2020-10-26T14:43:47.064-0800: 0.595: [GC concurrent-mark-end, 0.0017881 secs]
2020-10-26T14:43:47.064-0800: 0.595: [GC remark, 0.0017250 secs]
2020-10-26T14:43:47.066-0800: 0.598: [GC cleanup 227M->224M(512M), 0.0007712 secs]
2020-10-26T14:43:47.067-0800: 0.598: [GC concurrent-cleanup-start]
2020-10-26T14:43:47.067-0800: 0.598: [GC concurrent-cleanup-end, 0.0000403 secs]
2020-10-26T14:43:47.136-0800: 0.667: [GC pause (G1 Evacuation Pause) (young)-- 421M->329M(512M), 0.0082857 secs]
2020-10-26T14:43:47.146-0800: 0.677: [GC pause (G1 Evacuation Pause) (mixed) 333M->313M(512M), 0.0079071 secs]
2020-10-26T14:43:47.158-0800: 0.689: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 317M->315M(512M), 0.0051027 secs]
2020-10-26T14:43:47.163-0800: 0.694: [GC concurrent-root-region-scan-start]
2020-10-26T14:43:47.164-0800: 0.695: [GC concurrent-root-region-scan-end, 0.0003804 secs]
2020-10-26T14:43:47.164-0800: 0.695: [GC concurrent-mark-start]
2020-10-26T14:43:47.166-0800: 0.698: [GC concurrent-mark-end, 0.0028042 secs]
2020-10-26T14:43:47.168-0800: 0.699: [GC remark, 0.0068681 secs]
2020-10-26T14:43:47.175-0800: 0.707: [GC cleanup 328M->327M(512M), 0.0009281 secs]
2020-10-26T14:43:47.177-0800: 0.708: [GC concurrent-cleanup-start]
2020-10-26T14:43:47.177-0800: 0.708: [GC concurrent-cleanup-end, 0.0000375 secs]
2020-10-26T14:43:47.202-0800: 0.734: [GC pause (G1 Evacuation Pause) (young) 424M->349M(512M), 0.0070758 secs]
2020-10-26T14:43:47.214-0800: 0.745: [GC pause (G1 Evacuation Pause) (mixed) 367M->308M(512M), 0.0062662 secs]
2020-10-26T14:43:47.232-0800: 0.763: [GC pause (G1 Evacuation Pause) (mixed) 337M->285M(512M), 0.0084217 secs]
2020-10-26T14:43:47.246-0800: 0.777: [GC pause (G1 Evacuation Pause) (mixed) 310M->289M(512M), 0.0038417 secs]
2020-10-26T14:43:47.251-0800: 0.782: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 296M->290M(512M), 0.0017932 secs]
2020-10-26T14:43:47.253-0800: 0.784: [GC concurrent-root-region-scan-start]
2020-10-26T14:43:47.253-0800: 0.784: [GC concurrent-root-region-scan-end, 0.0001978 secs]
2020-10-26T14:43:47.253-0800: 0.785: [GC concurrent-mark-start]
2020-10-26T14:43:47.255-0800: 0.786: [GC concurrent-mark-end, 0.0018776 secs]
2020-10-26T14:43:47.255-0800: 0.787: [GC remark, 0.0020696 secs]
2020-10-26T14:43:47.258-0800: 0.789: [GC cleanup 302M->302M(512M), 0.0009702 secs]
2020-10-26T14:43:47.283-0800: 0.814: [GC pause (G1 Evacuation Pause) (young) 396M->319M(512M), 0.0063529 secs]
2020-10-26T14:43:47.293-0800: 0.825: [GC pause (G1 Evacuation Pause) (mixed) 335M->295M(512M), 0.0117631 secs]
2020-10-26T14:43:47.311-0800: 0.842: [GC pause (G1 Evacuation Pause) (mixed) 321M->304M(512M), 0.0046589 secs]
2020-10-26T14:43:47.315-0800: 0.847: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 304M->304M(512M), 0.0019786 secs]
2020-10-26T14:43:47.317-0800: 0.849: [GC concurrent-root-region-scan-start]
2020-10-26T14:43:47.318-0800: 0.849: [GC concurrent-root-region-scan-end, 0.0004572 secs]
2020-10-26T14:43:47.318-0800: 0.849: [GC concurrent-mark-start]
2020-10-26T14:43:47.321-0800: 0.853: [GC concurrent-mark-end, 0.0032363 secs]
2020-10-26T14:43:47.321-0800: 0.853: [GC remark, 0.0027703 secs]
2020-10-26T14:43:47.325-0800: 0.856: [GC cleanup 315M->315M(512M), 0.0011552 secs]
2020-10-26T14:43:47.349-0800: 0.880: [GC pause (G1 Evacuation Pause) (young) 401M->329M(512M), 0.0061077 secs]
2020-10-26T14:43:47.360-0800: 0.891: [GC pause (G1 Evacuation Pause) (mixed) 347M->310M(512M), 0.0104623 secs]
2020-10-26T14:43:47.372-0800: 0.904: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 319M->314M(512M), 0.0020123 secs]
2020-10-26T14:43:47.375-0800: 0.906: [GC concurrent-root-region-scan-start]
2020-10-26T14:43:47.375-0800: 0.906: [GC concurrent-root-region-scan-end, 0.0001823 secs]
2020-10-26T14:43:47.375-0800: 0.906: [GC concurrent-mark-start]
2020-10-26T14:43:47.377-0800: 0.908: [GC concurrent-mark-end, 0.0023490 secs]
2020-10-26T14:43:47.377-0800: 0.909: [GC remark, 0.0020862 secs]
2020-10-26T14:43:47.380-0800: 0.911: [GC cleanup 326M->326M(512M), 0.0006268 secs]
2020-10-26T14:43:47.401-0800: 0.932: [GC pause (G1 Evacuation Pause) (young) 406M->338M(512M), 0.0088963 secs]
2020-10-26T14:43:47.414-0800: 0.945: [GC pause (G1 Evacuation Pause) (mixed) 356M->324M(512M), 0.0080403 secs]
2020-10-26T14:43:47.422-0800: 0.953: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 325M->324M(512M), 0.0019025 secs]
2020-10-26T14:43:47.424-0800: 0.955: [GC concurrent-root-region-scan-start]
2020-10-26T14:43:47.424-0800: 0.955: [GC concurrent-root-region-scan-end, 0.0001754 secs]
2020-10-26T14:43:47.424-0800: 0.955: [GC concurrent-mark-start]
2020-10-26T14:43:47.427-0800: 0.958: [GC concurrent-mark-end, 0.0027848 secs]
2020-10-26T14:43:47.427-0800: 0.958: [GC remark, 0.0031278 secs]
2020-10-26T14:43:47.430-0800: 0.962: [GC cleanup 336M->336M(512M), 0.0005539 secs]
2020-10-26T14:43:47.448-0800: 0.979: [GC pause (G1 Evacuation Pause) (young) 408M->349M(512M), 0.0054739 secs]
2020-10-26T14:43:47.458-0800: 0.989: [GC pause (G1 Evacuation Pause) (mixed) 366M->333M(512M), 0.0077514 secs]
2020-10-26T14:43:47.466-0800: 0.997: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 335M->333M(512M), 0.0019638 secs]
2020-10-26T14:43:47.468-0800: 0.999: [GC concurrent-root-region-scan-start]
2020-10-26T14:43:47.468-0800: 0.999: [GC concurrent-root-region-scan-end, 0.0004479 secs]
2020-10-26T14:43:47.468-0800: 0.999: [GC concurrent-mark-start]
2020-10-26T14:43:47.471-0800: 1.003: [GC concurrent-mark-end, 0.0029629 secs]
2020-10-26T14:43:47.472-0800: 1.003: [GC remark, 0.0021805 secs]
2020-10-26T14:43:47.474-0800: 1.005: [GC cleanup 344M->344M(512M), 0.0007366 secs]
2020-10-26T14:43:47.489-0800: 1.020: [GC pause (G1 Evacuation Pause) (young) 406M->356M(512M), 0.0073848 secs]
2020-10-26T14:43:47.504-0800: 1.035: [GC pause (G1 Evacuation Pause) (mixed) 378M->337M(512M), 0.0079402 secs]
2020-10-26T14:43:47.513-0800: 1.044: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 342M->338M(512M), 0.0018448 secs]
2020-10-26T14:43:47.515-0800: 1.046: [GC concurrent-root-region-scan-start]
2020-10-26T14:43:47.515-0800: 1.046: [GC concurrent-root-region-scan-end, 0.0001732 secs]
2020-10-26T14:43:47.515-0800: 1.046: [GC concurrent-mark-start]
2020-10-26T14:43:47.517-0800: 1.049: [GC concurrent-mark-end, 0.0021599 secs]
2020-10-26T14:43:47.517-0800: 1.049: [GC remark, 0.0024645 secs]
2020-10-26T14:43:47.520-0800: 1.052: [GC cleanup 347M->346M(512M), 0.0006227 secs]
2020-10-26T14:43:47.521-0800: 1.052: [GC concurrent-cleanup-start]
2020-10-26T14:43:47.521-0800: 1.052: [GC concurrent-cleanup-end, 0.0000226 secs]
2020-10-26T14:43:47.534-0800: 1.065: [GC pause (G1 Evacuation Pause) (young) 401M->356M(512M), 0.0060951 secs]
2020-10-26T14:43:47.544-0800: 1.076: [GC pause (G1 Evacuation Pause) (mixed) 379M->345M(512M), 0.0075998 secs]
2020-10-26T14:43:47.552-0800: 1.083: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 346M->345M(512M), 0.0014029 secs]
2020-10-26T14:43:47.554-0800: 1.085: [GC concurrent-root-region-scan-start]
2020-10-26T14:43:47.554-0800: 1.085: [GC concurrent-root-region-scan-end, 0.0001589 secs]
2020-10-26T14:43:47.554-0800: 1.085: [GC concurrent-mark-start]
2020-10-26T14:43:47.556-0800: 1.087: [GC concurrent-mark-end, 0.0019426 secs]
2020-10-26T14:43:47.556-0800: 1.087: [GC remark, 0.0031452 secs]
2020-10-26T14:43:47.559-0800: 1.091: [GC cleanup 357M->357M(512M), 0.0007555 secs]
执行结束!共生成对象次数:6410
```

##### <font color=red>第 2 题</font>：使用压测工具(wrk或sb)，演练gateway-server-0.0.1-SNAPSHOT.jar 示例。 
本题将采用不同的GC策略，并采用512MB和4GB堆大小进行测试，来看效果。
- 工具解读
```
wrk -t8 -c40 -d60s http://localhost:8088/hello
-t 线程数
-c 连接数
-d 持续时间
### 结果解读
Latency：响应时间
Req/Sec：每个线程每秒钟的完成的请求数
Avg：平均
Max：最大
Stdev：标准差
+/- Stdev： 正负一个标准差占比
```
- 串型GC(Serial) 性能报告
```
####512MB测试结果
Running 1m test @ http://localhost:8088/hello
  8 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    49.01ms   70.58ms   1.04s    86.24%
    Req/Sec   244.66    127.13     1.18k    69.62%
  115687 requests in 1.00m, 29.26MB read
  Non-2xx or 3xx responses: 115687
Requests/sec:   1924.84
Transfer/sec:    498.49KB
####4GB测试结果
Running 1m test @ http://localhost:8088/hello
  8 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    55.66ms   98.72ms   1.30s    90.13%
    Req/Sec   232.71    126.23     0.95k    67.92%
  108557 requests in 1.00m, 27.45MB read
  Non-2xx or 3xx responses: 108557
Requests/sec:   1807.52
Transfer/sec:    468.11KB
```
- 并行GC
```
#### 512MB测试结果
Running 1m test @ http://localhost:8088/hello
  8 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    44.36ms   63.47ms 621.71ms   85.94%
    Req/Sec   269.41    134.00     0.91k    68.50%
  127529 requests in 1.00m, 32.25MB read
  Non-2xx or 3xx responses: 127529
Requests/sec:   2122.22
Transfer/sec:    549.60KB
#### 4GB测试结果
Running 1m test @ http://localhost:8088/hello
  8 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    44.32ms   68.63ms 909.47ms   87.53%
    Req/Sec   261.81    124.19     0.90k    68.21%
  123856 requests in 1.00m, 31.32MB read
  Non-2xx or 3xx responses: 123856
Requests/sec:   2062.20
Transfer/sec:    534.06KB
```
- CMS GC
```
##### 512MB测试结果
Running 1m test @ http://localhost:8088/hello
  8 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    48.62ms   67.67ms 704.64ms   85.41%
    Req/Sec   239.94    123.03     0.92k    69.16%
  113884 requests in 1.00m, 28.80MB read
  Non-2xx or 3xx responses: 113884
Requests/sec:   1895.15
Transfer/sec:    490.80KB
##### 4GB测试结果
Running 1m test @ http://localhost:8088/hello
  8 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    51.65ms   74.08ms 841.88ms   86.45%
    Req/Sec   217.84    117.21     1.10k    69.88%
  103004 requests in 1.00m, 26.05MB read
  Non-2xx or 3xx responses: 103004
Requests/sec:   1714.09
Transfer/sec:    443.92KB
```
- Garbage First(G1)
```
####512MB测试结果
Running 1m test @ http://localhost:8088/hello
  8 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    49.55ms   69.03ms 681.30ms   85.61%
    Req/Sec   228.04    117.40     1.20k    68.64%
  107804 requests in 1.00m, 27.26MB read
  Non-2xx or 3xx responses: 107804
Requests/sec:   1794.86
Transfer/sec:    464.83KB
#### 4G测试结果
Running 1m test @ http://localhost:8088/hello
  8 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    51.45ms   81.32ms   1.01s    87.87%
    Req/Sec   250.35    144.28     0.98k    65.77%
  117300 requests in 1.00m, 29.67MB read
  Non-2xx or 3xx responses: 117300
Requests/sec:   1951.95
Transfer/sec:    505.51KB
```

#### <font color=red>总结</font>：根据上述自己对于1和2的演示，写一段对于不同 GC 的总结，提交到 Github。
- 在小内存的环境下各个垃圾收集器的性能差异不大，但是在提升内存到4GB的时候仅G1垃圾收集器性能有提升10%左右


### 第二周：第 4 课作业实践
##### <font color=red>第 1 题</font>：(可选)运行课上的例子，以及 Netty 的例子，分析相关现象。

执行ioexample包下的各种情况IO类，阻塞单线程的性能最差，其次是用线程和线程池，使用netty实现性能最高
```
## 单线程
Running 30s test @ http://localhost:9001
  8 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   899.21ms  103.27ms 977.83ms   96.15%
    Req/Sec     7.42      7.24    30.00     79.86%
  805 requests in 30.10s, 73.88KB read
  Socket errors: connect 0, read 1281, write 28, timeout 0
Requests/sec:     26.74
Transfer/sec:      2.45KB

## 多线程
Running 30s test @ http://localhost:9002
  8 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    26.13ms    7.06ms 112.42ms   98.96%
    Req/Sec    20.35     13.05    90.00     80.02%
  4508 requests in 30.06s, 1.09MB read
  Socket errors: connect 0, read 45599, write 19, timeout 0
Requests/sec:    149.98
Transfer/sec:     37.14KB

## 线程池
Running 30s test @ http://localhost:9003
  8 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    25.02ms    2.59ms  56.91ms   85.14%
    Req/Sec    14.78     10.03    77.00     70.75%
  3041 requests in 30.06s, 0.93MB read
  Socket errors: connect 0, read 46647, write 19, timeout 0
Requests/sec:    101.16
Transfer/sec:     31.71KB

## netty
Running 30s test @ http://localhost:9005
  8 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    24.21ms    2.12ms  62.78ms   70.33%
    Req/Sec   207.21     13.16   252.00     78.98%
  49610 requests in 30.10s, 3.22MB read
Requests/sec:   1647.95
Transfer/sec:    109.43KB

```
##### <font color=red>第 2 题</font>：写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801，代码提交到 Github。

这里分别采用了HttpClient 和 OkHttp实现简单的get请求书写
- 具体代码可参考包ioexample.clientdemo中代码
- 测试代码在test包下