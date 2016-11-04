package fr.team12.mis;

/**
 * Utilitary class that represent a value that can be either of type "Right" or
 * "Left".
 * If the left value is not null, right must be null.
 * Else if right is not null, left must be null.
 */
public class Either<R, L>
{
    private R right; ///< The right value.
    private L left;  ///< The left value.

    /**
     * Constructor that set right and left value.
     *
     * @param right Right value
     * @param left Left value
     */
    public Either(R right, L left)
    {
        this.right = right;
        this.left = left;
    }

    /**
     * Default constructor that set rigth and left value to null.
     */
    public Either()
    {
        this(null, null);
    }

    /**
     * Return the right value.
     *
     * @return The right value.
     */
    public R getRight()
    {
        return right;
    }

    /**
     * Return the left value.
     *
     * @return the left value.
     */
    public L getLeft()
    {
        return left;
    }

    /**
     * Set the right value.
     *
     * @param right The new right value.
     */
    public void setRight(R right)
    {
        this.right = right;
    }

    /**
     * Set the left value.
     *
     * @param left The new left value.
     */
    public void setLeft(L left)
    {
        this.left = left;
    }
}
