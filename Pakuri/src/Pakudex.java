public class Pakudex {
    //Initialize number of pakuri to 0
    private int pakudexSize = 0;

    //New array of type Pakuri
    private Pakuri[] pakudex;

    public Pakudex()
    {
//Default size for the Pakudex is 20
        pakudex = new Pakuri[20];
    }

    public Pakudex(int capacity)
    {

        pakudex = new Pakuri[capacity];
//Initialize each slot to null
        for(int i = 0; i < getCapacity(); i++)
        {
            pakudex[i] = null;
        }
    }

    //Returns number of pakuri currently in the Pakudex
    public int getSize()
    {
        return this.pakudexSize;
    }

    //Returns the max number of pakuri the Pakudex can hold
    public int getCapacity()
    {
        return pakudex.length;
    }

    public String[] getSpeciesArray()
    {
//Exception if there are no Pakuri in Pakudex
        if(pakudexSize <= 0)
        {
            return null;
        }

//array of pakuri names
        String[] speciesArray = new String[pakudexSize];
        for(int i = 0; i < pakudexSize; i++)
        {
            if(pakudex[i] != null)
            {
                speciesArray[i] = pakudex[i].getSpecies();
            }
        }


        return speciesArray;
    }

    public int[] getStats(String species)
    {
//Initializing
        int[] stats = null;
        Pakuri requiredPakuri = null;

//no pakuri yet
        String[] speciesArray = getSpeciesArray();
        if(speciesArray == null)
        {
            return null;
        }


//received Pakuri
        for(int i = 0; i < pakudexSize; i++)
        {
            if(pakudex[i] != null)
            {
                String pakuriSpecies = pakudex[i].getSpecies();
                if(pakuriSpecies.contentEquals(species))
                {
                    requiredPakuri = pakudex[i];
                }

            }
        }

//If Pakuri doesn't exist
        if(requiredPakuri == null)
        {
            return null;
        }

//get stats of Pakuri
        for(int i = 0; i < pakudexSize; i++)
        {
            if(speciesArray[i] != null)
            {
                if (speciesArray[i].contentEquals(species))
                {
                    int A = requiredPakuri.getAttack();
                    int D = requiredPakuri.getDefense();
                    int S = requiredPakuri.getSpeed();
                    stats = new int[]{A, D, S};
                }
            }
        }

        return stats; //Return array of stats
    }

    public void sortPakuri()
    {
//Compares the first Pakuri with the next in a series, switches if necessary
        for(int i = 0; i < pakudexSize - 1; i++)
        {
            for(int j = i + 1; j < pakudexSize; j++)
            {
                if(pakudex[i] != null && pakudex[j] != null)
                {
                    String firstSpecies = pakudex[i].getSpecies();
                    String nextSpecies = pakudex[j].getSpecies();
                    if (firstSpecies.compareTo(nextSpecies) > 0)
                    {
                        Pakuri placeHolder = pakudex[j];
                        pakudex[j] = pakudex[i];
                        pakudex[i] = placeHolder;
                    }
                }
            }
        }
    }

    public boolean addPakuri(String species)
    {
//Exception if pakudex is full handled in PakuriProgram because its checked before method is applied
//Exception if the species already exists
        for(int i = 0; i < pakudexSize; i++)
        {
            if(pakudex[i] != null)
            {
                String exists = pakudex[i].getSpecies();

                if(exists.contentEquals(species))
                {
                    return false;
                }
            }
        }

//If no exceptions, add species
        pakudex[pakudexSize] = new Pakuri(species);
        pakudexSize = pakudexSize + 1;
        return true;
    }

    public boolean evolveSpecies(String species)
    {
//Initialize
        Pakuri requiredPakuri = null;

//Exception if there are no Pakuri in Pakudex
        if(pakudexSize <= 0)
        {
            return false;
        }

//Obtain required Pakuri
        for(int i = 0; i < pakudexSize; i++)
        {
            if(pakudex[i] != null)
            {
                String exists = pakudex[i].getSpecies();
                if(exists.contentEquals(species))
                {
                    requiredPakuri = pakudex[i];
                }

            }
        }

//Exception if the Pakuri doesn't exist
        if(requiredPakuri == null)
        {
            return false;
        }

        requiredPakuri.evolve();
        return true;

    }
}
