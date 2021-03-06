import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Splitter implements Writable,WritableComparable<Splitter>
{
	private Text word,next;

	 public Splitter(Text word, Text next)
	{
        this.word = word;
        this.next = next;
    }

    public Splitter(String word, String next)
	{
        this(new Text(word),new Text(next));
    }

    public Splitter()
	{
        this.word = new Text();
        this.next = new Text();
    }

    @Override
    public int compareTo(Splitter other)
	{
        int returnVal = this.word.compareTo(other.getWord());
        if(returnVal != 0)
            return returnVal;
		
        if(this.next.toString().equals("*"))
            return -1;
		else
			if(other.getNext().toString().equals("*"))
				return 1;
			else
				return this.next.compareTo(other.getNext());
    }


    public static Splitter read(DataInput in) throws IOException
	{
        Splitter wordPair = new Splitter();
        wordPair.readFields(in);
        return wordPair;
    }

    @Override
    public void write(DataOutput out) throws IOException
	{
        word.write(out);
        next.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException
	{
        word.readFields(in);
        next.readFields(in);
    }

    @Override
    public String toString()
	{
          return (word + " " + next); 
    }

    @Override
    public boolean equals(Object o)
	{
        if (this == o)
			return true;
        if (o == null || getClass() != o.getClass())
			return false;

        Splitter wordPair = (Splitter) o;

        if (next != null ? !next.equals(wordPair.next) : wordPair.next != null)
			return false;
        if (word != null ? !word.equals(wordPair.word) : wordPair.word != null)
			return false;

        return true;
    }

    @Override
    public int hashCode()
	{
        int result = word != null ? word.hashCode() : 0;
        result = 163 * result + (next != null ? next.hashCode() : 0);
        return result;
    }

    public void setWord(String word)
	{
        this.word.set(word);
    }
    public void setNext(String next)
	{
        this.next.set(next);
    }

    public Text getWord()
	{
        return word;
    }

    public Text getNext()
	{
        return next;
    }
}