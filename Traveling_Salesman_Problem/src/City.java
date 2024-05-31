// Clasa pentru reprezentarea unui oras din problema
public class City
{
        String name;
        int index;

        public City(String name, int index)
        {
            this.name = name;
            this.index = index;
        }

        @Override
        public String toString()
        {
            return name;
        }


}
