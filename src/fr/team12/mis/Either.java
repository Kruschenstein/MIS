package fr.team12.mis;

public class Either<R, L>
{
    private R right;
    private L left;

    public Either(R right, L left)
    {
        this.right = right;
        this.left = left;
    }

    public Either()
    {
        this(null, null);
    }

    public R getRight()
    {
        return right;
    }

    public L getLeft()
    {
        return left;
    }

    public void setRight(R right)
    {
        this.right = right;
    }

    public void setLeft(L left)
    {
        this.left = left;
    }
}
