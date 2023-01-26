package datastructure.string;

/******************************************************************************
 *  Reads in two strings, the pattern and the input text, and
 *  searches for the pattern in the input text using the
 *  KMP algorithm.
 *
 *  % java KMP abracadabra abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad 
 *  pattern:               abracadabra          
 *
 *  % java KMP rab abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad 
 *  pattern:         rab
 *
 *  % java KMP bcara abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad 
 *  pattern:                                   bcara
 *
 *  % java KMP rabrabracad abacadabrabracabracadabrabrabracad 
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:                        rabrabracad
 *
 *  % java KMP abacad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern: abacad
 *
 ******************************************************************************/
public class KMP {
    public static int kmp(String txt, String pattern) {
        if (pattern.isEmpty()) {
            return 0;
        }

        int n = txt.length();
        int m = pattern.length();

        /*
         * 真前缀：不等于自身的的前缀。
         * 真后缀：不等于自身的的后缀。
         * next[i]表示pattern的子串pattern[0:i]最长的一对相等的真前缀和真后缀的长度
         * 例如，对于pattern为 aabaaab，
         * 其子串[0:0]为 a，没有真前缀和真后缀，根据规定长度为 0
         * 其子串[0:4]为 aabaa，最长的一对相等的真前后缀为 aa，长度为 2
         */
        // 构建next数组
        int[] next = new int[m];
        // next[0] = 0;
        for (int i = 1, j = 0; i < m; i++) {
            // i用来遍历子串[0:i]
            // 开始时的j表示前一个子串的相等真前后缀的长度
            // 开始时的j也表示前一个子串真前缀之后的一个字符的下标
            // j的范围为 [0,i)

            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                // 这个循环的目的是，
                // 要找到能够与当前子串与相比于上一个子串新增的字符pattern[i]相等的前面某个子串的真前缀的后一个字符pattern[j]

                // 为什么是 j = next[j - 1]，而不是j--之类的呢？
                // 原因是pattern[j]得是前面某个子串的真前缀之后的一个字符
                j = next[j - 1];
            }

            // 退出循环的其中一个情况时j == 0，那么当前子串就没有一对相等的真前后缀，
            // 则next[i] = 0（因为此时j=0，所以也可以表示为next[i] = j）

            // 退出循环的另一个条件是找到了前面某个子串的真前缀的后一个字符pattern[j]，和pattern[i]相等
            // （此时的j也表示前面那个子串的最长的一对相等的真前后缀的长度）
            // 那么next[i]就应该等于j+1，同时j也要+1，即j++后，next[i]=j
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            next[i] = j;
        }

        /*
         * i指向txt字符串的字符，i始终++
         * j指向pattern字符串的字符
         */
        for (int i = 0, j = 0; i < n; i++) {
            // 如果碰到txt[i]!=pattern[j]，
            // 也就是对于字符串txt[0:i]的后缀没有与pattern[0:j]的前缀相等
            // 那么回退j
            while (j > 0 && txt.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }


            // 回退到前面某个pattern[0:j]，txt[i]与pattern[j]相等，j指向下一个字符
            if (txt.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            // 如果j已经指到了pattern字符串的末尾，就说明匹配到了
            // 并且在txt中匹配到的pattern的最后一个字符的下标为i
            // 那么开始下标就是 i-m+1
            if (j == m) {
                return i - m + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String txt = "abacadabrabracabracadabrabrabracad";
        String pattern = "abracadabra";
        int startIndex = KMP.kmp(txt, pattern);
        System.out.println("startIndex = " + startIndex);
    }
}