public class Hypothesis 
{
    private String description;
    private Relevance[] supportingItems;
    private byte probability;

    // Getters
    public String getDescription() {
        return this.description;
    }
    public Relevance[] getSupportingItems() {
        return this.supportingItems;
    }
    public byte getProbability() {
        return this.probability;
    }

    // Setters
    public void setDescription(String description) {
        this.description = description;
    }
    public void setSupportingItems(Relevance[] supportingItems) {
        this.supportingItems = supportingItems;
    }
    public void setProbability(byte probability) {
        if (probability >= 0 && probability <= 5) {
            this.probability = probability;
        }
        else if (probability < 0) {
            this.probability = 0;
        }
        else {
            this.probability = 5;
        }
    }
    public void setProbability() {
        if (this.supportingItems != null) {
            this.probability = this.supportingItems[0].getInferentialForce();
            for (int i = 0; i < supportingItems.length; ++i) {
                if (this.supportingItems[i].getInferentialForce() < this.probability) {
                    this.probability = this.supportingItems[i].getInferentialForce();
                }
            }
        } 
    }

    // Constructors
    public Hypothesis (String description, Relevance[] supportingItems) {
        this.description = description;
        this.supportingItems = supportingItems;
    }
    public Hypothesis (String description) {
        this.description = description;
        this.probability = 0;
    }

    // Methods
    public void addSupportingItem(Relevance supportingItem) {
        Relevance[] supportingItems;
        if (this.supportingItems != null) {
            supportingItems = new Relevance[this.supportingItems.length + 1];
            for (int i = 0; i < this.supportingItems.length; ++i) { // For loop makes a copy of the array
                supportingItems[i] = this.supportingItems[i];
            }
            supportingItems[this.supportingItems.length] = supportingItem; // adds item to new array
            this.supportingItems = supportingItems;
            setProbability();
        }
        else {
            supportingItems = new Relevance[1];
            supportingItems[0] = supportingItem; // adds item to new array
            this.supportingItems = supportingItems;
            setProbability();

        }
        
    }
    public byte computeProbability() {
        setProbability();
        return this.probability;
    }
    public String toString() {
        String ret = "Hypothesis: ";
        ret += "\n** Description: " + getDescription();
        ret += "\n** Probability: " + computeProbability() + " -> " + Evidence.probability2String(getProbability()).toLowerCase();
        return ret;
    }
}
