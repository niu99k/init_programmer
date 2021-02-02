/*
题目描述
给定一个长度为偶数的数组arr，长度记为2*N。前n个为左部分，后n个为右部分。arr可以表示为\{L_1,L_2...L_{n-1},L_n,R_1,R_2...R_{n-1},R_n \}{L
1
​
 ,L
2
​
 ...L
n−1
​
 ,L
n
​
 ,R
1
​
 ,R
2
​
 ...R
n−1
​
 ,R
n
​
 },请将数组调整成\{ R_1,L_1,R_2,L_2,...R_n,L_n \}{R
1
​
 ,L
1
​
 ,R
2
​
 ,L
2
​
 ,...R
n
​
 ,L
n
​
 }。
输入描述:
输入有包含两行，第一行包含一个整数n（1  \leq n \leq 10^5 ，保证n为偶数）（1≤n≤10
5
 ，保证n为偶数），代表arr长度。第二行包含n个整数，代表数组arr（1 \leq arr_i \leq 10^8）（1≤arr
i
​
 ≤10
8
 ）。
输出描述:
输出一行n个整数，代表调整后的数组arr。
示例1
输入
复制
6
1 2 3 4 5 6
输出
复制
4 1 5 2 6 3
备注:
时间复杂度O（N）O（N），额外空间复杂度O（1）O（1）。

800
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99 100 101 102 103 104 105 106 107 108 109 110 111 112 113 114 115 116 117 118 119 120 121 122 123 124 125 126 127 128 129 130 131 132 133 134 135 136 137 138 139 140 141 142 143 144 145 146 147 148 149 150 151 152 153 154 155 156 157 158 159 160 161 162 163 164 165 166 167 168 169 170 171 172 173 174 175 176 177 178 179 180 181 182 183 184 185 186 187 188 189 190 191 192 193 194 195 196 197 198 199 200 201 202 203 204 205 206 207 208 209 210 211 212 213 214 215 216 217 218 219 220 221 222 223 224 225 226 227 228 229 230 231 232 233 234 235 236 237 238 239 240 241 242 243 244 245 246 247 248 249 250 251 252 253 254 255 256 257 258 259 260 261 262 263 264 265 266 267 268 269 270 271 272 273 274 275 276 277 278 279 280 281 282 283 284 285 286 287 288 289 290 291 292 293 294 295 296 297 298 299 300 301 302 303 304 305 306 307 308 309 310 311 312 313 314 315 316 317 318 319 320 321 322 323 324 325 326 327 328 329 330 331 332 333 334 335 336 337 338 339 340 341 342 343 344 345 346 347 348 349 350 351 352 353 354 355 356 357 358 359 360 361 362 363 364 365 366 367 368 369 370 371 372 373 374 375 376 377 378 379 380 381 382 383 384 385 386 387 388 389 390 391 392 393 394 395 396 397 398 399 400 401 402 403 404 405 406 407 408 409 410 411 412 413 414 415 416 417 418 419 420 421 422 423 424 425 426 427 428 429 430 431 432 433 434 435 436 437 438 439 440 441 442 443 444 445 446 447 448 449 450 451 452 453 454 455 456 457 458 459 460 461 462 463 464 465 466 467 468 469 470 471 472 473 474 475 476 477 478 479 480 481 482 483 484 485 486 487 488 489 490 491 492 493 494 495 496 497 498 499 500 501 502 503 504 505 506 507 508 509 510 511 512 513 514 515 516 517 518 519 520 521 522 523 524 525 526 527 528 529 530 531 532 533 534 535 536 537 538 539 540 541 542 543 544 545 546 547 548 549 550 551 552 553 554 555 556 557 558 559 560 561 562 563 564 565 566 567 568 569 570 571 572 573 574 575 576 577 578 579 580 581 582 583 584 585 586 587 588 589 590 591 592 593 594 595 596 597 598 599 600 601 602 603 604 605 606 607 608 609 610 611 612 613 614 615 616 617 618 619 620 621 622 623 624 625 626 627 628 629 630 631 632 633 634 635 636 637 638 639 640 641 642 643 644 645 646 647 648 649 650 651 652 653 654 655 656 657 658 659 660 661 662 663 664 665 666 667 668 669 670 671 672 673 674 675 676 677 678 679 680 681 682 683 684 685 686 687 688 689 690 691 692 693 694 695 696 697 698 699 700 701 702 703 704 705 706 707 708 709 710 711 712 713 714 715 716 717 718 719 720 721 722 723 724 725 726 727 728 729 730 731 732 733 734 735 736 737 738 739 740 741 742 743 744 745 746 747 748 749 750 751 752 753 754 755 756 757 758 759 760 761 762 763 764 765 766 767 768 769 770 771 772 773 774 775 776 777 778 779 780 781 782 783 784 785 786 787 788 789 790 791 792 793 794 795 796 797 798 799 800


 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static String[] array;
    static int arrayCount;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        solve();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayCount; i++) {
            stringBuffer.append(array[i] + " ");
        }
        System.out.print(stringBuffer.substring(0, stringBuffer.length() - 1));
    }

    private static void solve() {
        shuffle(0, arrayCount - 1);
    }


    private static void shuffle(int beginIndex, int endIndex) {
        if (beginIndex < endIndex) {
            final int count = endIndex - beginIndex + 1;
            final int maxCount = isCountLoopBeginIndex(count);
            if (maxCount == count) {
                replaceOneByOneWithSaction(beginIndex, endIndex);
            } else {
                replaceBlock(beginIndex, endIndex, maxCount);
                replaceOneByOneWithSaction(beginIndex, beginIndex + maxCount - 1);
                shuffle(beginIndex + maxCount, endIndex);
            }
        }
    }

    private static void replaceBlock(int beginIndex, int endIndex, int maxCount) {
        reverse(beginIndex + maxCount / 2, beginIndex + maxCount / 2 + ((endIndex - beginIndex + 1) / 2 - maxCount / 2) - 1);
        reverse(beginIndex + (endIndex - beginIndex + 1) / 2, beginIndex + (endIndex - beginIndex + 1) / 2 + maxCount / 2 - 1);
        reverse(beginIndex + maxCount / 2, beginIndex + (endIndex - beginIndex + 1) / 2 + maxCount / 2 - 1);
    }

    private static void reverse(int beginIndex, int endIndex) {
        while (endIndex >= beginIndex) {
            String temp = array[beginIndex];
            array[beginIndex] = array[endIndex];
            array[endIndex] = temp;
            beginIndex++;
            endIndex--;
        }
    }

    private static int isCountLoopBeginIndex(int count) {
        int result = 2;
        int base = 1;
        while (true) {
            if (base * 3 - 1 > count) {
                result = base - 1;
                break;
            }
            if (count == base * 3 - 1) {
                result = base * 3 - 1;
                break;
            }
            base *= 3;
        }
        return result;
    }

    private static void replaceOneByOneWithSaction(int beginIndex, int endIndex) {
        final int count = endIndex - beginIndex + 1;
        final int beginIndex4Loop = beginIndex;
        int base = 1;
        while (true) {
            beginIndex = (base - 1);
            if (beginIndex4Loop + beginIndex >= endIndex) {
                break;
            }
            replaceOneByOne(beginIndex4Loop, beginIndex4Loop + beginIndex, count);
            base = base * 3;
        }
    }


    private static void replaceOneByOne(int beginIndex4Loop, int pushIndex, int count) {
        int endIndex = pushIndex;
        pushIndex = nextPushIndex(beginIndex4Loop, pushIndex, count);
        String tempPre = array[endIndex];
        String tempNext = "";
        while (true) {
            if (pushIndex == endIndex) {
                array[pushIndex] = tempPre;
                break;
            } else {
                tempNext = array[pushIndex];
                array[pushIndex] = tempPre;
                tempPre = tempNext;
                pushIndex = nextPushIndex(beginIndex4Loop, pushIndex, count);

            }
        }
    }

    private static int nextPushIndex(int beginIndex, int pushIndex, int count) {
        int result;
        final int middleIndex = beginIndex + count / 2;
        if (pushIndex >= middleIndex) {
            result = (pushIndex - middleIndex) * 2 + beginIndex;
        } else {
            result = (pushIndex - beginIndex) * 2 + 1 + beginIndex;
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        arrayCount = scanner.nextInt();
        array = new String[arrayCount];
        for (int i = 0; i < arrayCount; i++) {
            array[i] = scanner.next();
        }
    }
}