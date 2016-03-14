package me.chuvashev.databindingutils;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 12/09/15
 * Time: 20:51
 */
public class Objects {

    /**
     * Null-safe equivalent of {@code a.equals(b)}.
     */
    public static boolean equals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

}
