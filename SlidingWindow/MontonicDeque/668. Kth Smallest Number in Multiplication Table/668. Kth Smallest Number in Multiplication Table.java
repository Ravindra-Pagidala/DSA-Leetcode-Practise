1class Solution {
2    public int findKthNumber(int m, int n, int k) {
3        long start = 1, end = m*n, mid = 0;
4        int min = Math.min(m,n);
5        int max = Math.max(m,n);
6    /*in multiplication matrix , everything is symmertical 
7    if m = 3000, n= 5, instead of iterating 3000 rows, we can iterate over 5 rows */
8        while(start<end) {
9            mid = start+(end-start)/2;
10            if(count(mid,min,max,k)){
11                end = mid;
12            } else{
13                start = mid+1;
14            }
15        }
16        return (int)start;
17    }
18
19    public boolean count(long mid, int min, int max, int k) {
20        int count =0;
21        for(int i =1 ; i<=min ; i++) {
22           count+=Math.min(mid/i, max);
23           if(count>=k) return true;
24        }
25     
26        return false;
27    }
28}
29
30/*
31The **Kth Smallest Number in Multiplication Table** is one of the best problems to understand **Binary Search on Answer**.
32
33Let's understand it from scratch.
34
35---
36
37# Problem Statement
38
39Given two integers `m` and `n`, imagine a multiplication table.
40
41Example:
42
43```
44m = 3, n = 3
45
46      1   2   3
47    -------------
481 |   1   2   3
492 |   2   4   6
503 |   3   6   9
51```
52
53The table values are
54
55```
561 2 3
572 4 6
583 6 9
59```
60
61If sorted,
62
63```
641 2 2 3 3 4 6 6 9
65```
66
67For
68
69```
70k = 5
71```
72
73Answer is
74
75```
763
77```
78
79---
80
81# Brute Force
82
83Generate all numbers.
84
85```
86m*n numbers
87```
88
89Sort them.
90
91Time
92
93```
94O(m*n log(m*n))
95```
96
97Impossible when
98
99```
100m,n = 30000
101```
102
103because
104
105```
10630000 × 30000 = 900 million elements
107```
108
109Need another approach.
110
111---
112
113# Observe the table
114
115Example
116
117```
1181   2   3   4
1192   4   6   8
1203   6   9   12
1214   8   12  16
122```
123
124Rows are sorted.
125
126Columns are sorted.
127
128This usually hints at
129
130> Binary Search
131
132But what do we binary search?
133
134Not indices.
135
136We binary search the **answer value**.
137
138---
139
140# Binary Search on Answer
141
142Suppose we guess
143
144```
145mid = 5
146```
147
148Question becomes
149
150> How many numbers in the table are ≤ 5 ?
151
152If count
153
154```
155>= k
156```
157
158Then
159
160```
161answer <= 5
162```
163
164Otherwise
165
166```
167answer > 5
168```
169
170Exactly the same template used in
171
172* Koko Eating Bananas
173* Capacity to Ship Packages
174* Minimum Time to Complete Trips
175* Aggressive Cows
176
177---
178
179# Important Counting Trick
180
181Suppose
182
183```
184m = 4
185n = 5
186```
187
188Table
189
190```
1911  2  3  4  5
1922  4  6  8 10
1933  6  9 12 15
1944  8 12 16 20
195```
196
197Suppose
198
199```
200mid = 8
201```
202
203Need count of numbers ≤8.
204
205---
206
207## Row 1
208
209```
2101 2 3 4 5
211```
212
213All
214
215```
2165
217```
218
219numbers are ≤8.
220
221---
222
223## Row 2
224
225```
2262 4 6 8 10
227```
228
229Numbers ≤8
230
231```
2322 4 6 8
233```
234
235Count
236
237```
2384
239```
240
241---
242
243## Row 3
244
245```
2463 6 9 12 15
247```
248
249Numbers ≤8
250
251```
2523 6
253```
254
255Count
256
257```
2582
259```
260
261---
262
263## Row 4
264
265```
2664 8 12 16 20
267```
268
269Numbers ≤8
270
271```
2724 8
273```
274
275Count
276
277```
2782
279```
280
281Total
282
283```
2845+4+2+2 = 13
285```
286
287---
288
289# How to calculate row count?
290
291Suppose row = i.
292
293Values are
294
295```
296i×1
297i×2
298i×3
299...
300i×n
301```
302
303Need
304
305```
306i×j <= mid
307```
308
309So
310
311```
312j <= mid/i
313```
314
315Maximum column
316
317```
318mid/i
319```
320
321But row has only
322
323```
324n columns
325```
326
327Therefore
328
329```
330count = min(mid/i, n)
331```
332
333This is the key observation.
334
335---
336
337## Example
338
339Row
340
341```
3423
343
3443 6 9 12 15
345```
346
347mid
348
349```
3508
351```
352
353```
3548/3 = 2
355```
356
357Meaning
358
359```
3603×1
3613×2
362```
363
364Only first two numbers.
365
366```
367min(2,5)=2
368```
369
370Correct.
371
372---
373
374## Another Example
375
376Row
377
378```
3792
380
3812 4 6 8 10
382```
383
384mid
385
386```
387100
388```
389
390```
391100/2=50
392```
393
394But row has only
395
396```
3975
398```
399
400columns.
401
402So
403
404```
405min(50,5)=5
406```
407
408Correct.
409
410---
411
412# Binary Search
413
414Search space
415
416Minimum value
417
418```
4191
420```
421
422Maximum value
423
424```
425m*n
426```
427
428```
429low = 1
430high = m*n
431```
432
433While
434
435```
436low < high
437```
438
439Take
440
441```
442mid
443```
444
445Count elements
446
447```
448<= mid
449```
450
451If
452
453```
454count >= k
455```
456
457Then
458
459```
460mid may be answer
461```
462
463Move left
464
465```
466high = mid
467```
468
469Else
470
471```
472low = mid+1
473```
474
475At end
476
477```
478low==high
479```
480
481Answer.
482
483---
484
485# Dry Run
486
487Example
488
489```
490m=3
491n=3
492k=5
493```
494
495Search
496
497```
4981...9
499```
500
501---
502
503### mid=5
504
505Count
506
507Row1
508
509```
510min(5/1,3)=3
511```
512
513Row2
514
515```
516min(5/2,3)=2
517```
518
519Row3
520
521```
522min(5/3,3)=1
523```
524
525Total
526
527```
5286
529```
530
531Need
532
533```
5345
535```
536
537Enough.
538
539Search left.
540
541```
542high=5
543```
544
545---
546
547### mid=3
548
549Count
550
551Row1
552
553```
5543
555```
556
557Row2
558
559```
5601
561```
562
563Row3
564
565```
5661
567```
568
569Total
570
571```
5725
573```
574
575Enough.
576
577```
578high=3
579```
580
581---
582
583### mid=2
584
585Row1
586
587```
5882
589```
590
591Row2
592
593```
5941
595```
596
597Row3
598
599```
6000
601```
602
603Total
604
605```
6063
607```
608
609Too small.
610
611Need larger.
612
613```
614low=3
615```
616
617Now
618
619```
620low=high=3
621```
622
623Answer
624
625```
6263
627```
628
629---
630
631# Why use `count >= k`?
632
633Suppose
634
635```
636Sorted numbers
637
6381 2 2 3 3 4 6 6 9
639```
640
641Need
642
643```
644k=5
645```
646
647Answer
648
649```
6503
651```
652
653Now suppose
654
655```
656mid=4
657```
658
659Numbers ≤4
660
661```
6621 2 2 3 3 4
663```
664
665Count
666
667```
6686
669```
670
671Since
672
673```
6746>=5
675```
676
677The answer **could still be 3**, so we keep searching to the left.
678
679If you instead moved right when `count == k`, you'd skip the correct answer.
680
681---
682
683# Complexity
684
685Counting
686
687```
688O(m)
689```
690
691Binary search
692
693```
694log(m*n)
695```
696
697Overall
698
699```
700O(m log(m*n))
701```
702
703Space
704
705```
706O(1)
707```
708
709Your solution is actually a **slightly optimized version** of the standard solution. Let's compare them carefully.
710
711---
712
713# Standard Solution
714
715```java
716public int findKthNumber(int m, int n, int k) {
717    int low = 1;
718    int high = m * n;
719
720    while (low < high) {
721        int mid = low + (high - low) / 2;
722
723        if (countLessOrEqual(mid, m, n) >= k)
724            high = mid;
725        else
726            low = mid + 1;
727    }
728
729    return low;
730}
731
732private int countLessOrEqual(int mid, int m, int n) {
733    int count = 0;
734
735    for (int row = 1; row <= m; row++) {
736        count += Math.min(mid / row, n);
737    }
738
739    return count;
740}
741```
742
743---
744
745# Your Solution
746
747```java
748int mm = Math.min(m, n);
749int nn = Math.max(m, n);
750```
751
752Instead of iterating over `m` rows, you iterate over the **smaller dimension**.
753
754Then
755
756```java
757cnt += Math.min(val / i, n);
758```
759
760where `n` is actually the larger dimension (`nn`).
761
762---
763
764# Difference 1: Iterate over the smaller dimension
765
766Suppose
767
768```text
769m = 30000
770n = 5
771```
772
773### Standard solution
774
775Loops
776
777```text
77830000 times
779```
780
781### Your solution
782
783```text
784mm = 5
785nn = 30000
786```
787
788Loops
789
790```text
7915 times
792```
793
794Huge improvement.
795
796---
797
798Why is this valid?
799
800Suppose original table
801
802```text
8031 2 3 4 5
804
8052 4 6 8 10
806
8073 6 9 12 15
808
809...
810
81130000 ...
812```
813
814Instead of thinking
815
816```text
81730000 rows
8185 columns
819```
820
821You think
822
823```text
8245 rows
82530000 columns
826```
827
828The multiplication table is symmetric:
829
830```text
831i × j = j × i
832```
833
834So nothing changes.
835
836---
837
838# Difference 2: Boolean vs Integer
839
840Standard
841
842```java
843count(mid) >= k
844```
845
846returns an integer.
847
848Your solution
849
850```java
851boolean count(...)
852```
853
854returns
855
856```java
857true
858```
859
860as soon as
861
862```java
863cnt >= k
864```
865
866---
867
868Example
869
870Suppose
871
872```text
873k = 500
874```
875
876After row 120
877
878```text
879cnt = 503
880```
881
882Standard solution
883
884continues computing
885
886```text
887row121
888row122
889...
890rowm
891```
892
893Your solution
894
895immediately
896
897```java
898return true;
899```
900
901No extra work.
902
903---
904
905# Difference 3: Early Stopping
906
907Your code
908
909```java
910if(cnt >= k){
911    return true;
912}
913```
914
915This can save a lot of iterations.
916
917Suppose
918
919```text
920m = 30000
921```
922
923and
924
925```text
926mid
927```
928
929is large.
930
931Maybe after only
932
933```text
934800 rows
935```
936
937you already have
938
939```text
940cnt >= k
941```
942
943No need to examine the remaining
944
945```text
94629200 rows.
947```
948
949---
950
951# Complexity Comparison
952
953## Standard
954
955Binary search
956
957```text
958O(log(m*n))
959```
960
961Each iteration
962
963```text
964O(m)
965```
966
967Overall
968
969```text
970O(m log(m*n))
971```
972
973---
974
975## Your Solution
976
977Each iteration
978
979```text
980O(min(m,n))
981```
982
983because of
984
985```java
986Math.min(m,n)
987```
988
989Sometimes even less because of
990
991```java
992return true;
993```
994
995Overall
996
997```text
998O(min(m,n) × log(m*n))
999```
1000
1001which is strictly better.
1002
1003---
1004
1005# Example
1006
1007Suppose
1008
1009```text
1010m = 30000
1011n = 5
1012```
1013
1014Standard
1015
1016```
101730000 iterations
1018```
1019
1020Your solution
1021
1022```
10235 iterations
1024```
1025
1026Nearly **6000× fewer loop iterations**.
1027
1028---
1029
1030# One small improvement
1031
1032Instead of
1033
1034```java
1035int high = n * m;
1036```
1037
1038write
1039
1040```java
1041int high = m * n;
1042```
1043
1044or even
1045
1046```java
1047long high = (long) m * n;
1048```
1049
1050Using `long` is safer in general because integer multiplication can overflow for larger constraints (even though for this specific problem `m, n <= 30000`, so `30000 × 30000 = 900,000,000`, which still fits in an `int`).
1051
1052---
1053
1054## Interview perspective
1055
1056If you present your solution in an interview, mentioning these two optimizations leaves a strong impression:
1057
10581. **Iterate over the smaller dimension** because the multiplication table is symmetric (`i × j = j × i`), reducing the per-iteration complexity from `O(m)` to `O(min(m, n))`.
10592. **Return early** once the count reaches `k`, since the binary search only needs to know whether `count >= k`, not the exact count.
1060
1061These optimizations don't change the correctness but can significantly improve performance on skewed tables like `30000 × 5`.
1062
1063*/
1064