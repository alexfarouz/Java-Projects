public class Evidence 
{
    private String description;
    private byte type;  // Type represents the 4 different types of evidence
    private byte unambiguity;
    private byte credibility;
    private byte completeness;
    private byte conclusiveness;
    private byte probability;

    // Getters
    public String getDescription() {
        return this.description;
    }
    public byte getType() {
        return this.type;
    }
    public byte getUnambiguity() {
        return this.unambiguity;
    }
    public byte getCredibility() {
        return this.credibility;
    }
    public byte getCompleteness() {
        return this.completeness;
    }
    public byte getConclusiveness() {
        return this.conclusiveness;
    }
    public byte getProbability() {
        return this.probability;
    }

    // Setters
    public void setDescription(String description) {
        this.description = description;
    }
    public void setType(byte type) {
        if (type <= 5 && type >= 1) {
            this.type = type;
        }
        else {
            this.type = 5;
        }
    }
    public void setUnambiguity(byte unambiguity) {
        if (unambiguity > 5) {
            this.unambiguity = 5;
        }
        else if (unambiguity < 0) {
            this.unambiguity = 0;
        }
        else {
            this.unambiguity = unambiguity;
        }
    } 
    public void setCredibility(byte credibility) {
        if (credibility > 5) {
            this.credibility = 5;
        }
        else if (credibility < 0) {
            this.credibility = 0;
        }
        else {
            this.credibility = credibility;
        }
    }
    public void setCompleteness(byte completeness) {
        if (completeness > 5) {
            this.completeness = 5;
        }
        else if (completeness < 0) {
            this.completeness = 0;
        }
        else {
            this.completeness = completeness;
        }
    }
    public void setConclusiveness(byte conclusiveness) {
        if (conclusiveness > 5) {
            this.conclusiveness = 5;
        }
        else if (conclusiveness < 0) {
            this.conclusiveness = 0;
        }
        else {
            this.conclusiveness = conclusiveness;
        }
    }

    // Constructors
    public Evidence (String description, byte type, byte unambiguity, byte credibility, byte completeness, byte conclusiveness) {
        this.description = description;
        this.type = type;
        this.unambiguity = unambiguity;
        this.credibility = credibility;
        this.completeness = completeness;
        this.conclusiveness = conclusiveness;
    }
    public Evidence (String description, byte unambiguity, byte credibility, byte completeness, byte conclusiveness) {
        this.description = description;
        this.unambiguity = unambiguity;
        this.credibility = credibility;
        this.completeness = completeness;
        this.conclusiveness = conclusiveness;
        this.type = 5;
    }

    // Methods
    public byte computeProbability() {
        double avg = 0;
        if (getType() == (byte)5) {
            avg = 0.25*(this.unambiguity+this.credibility+this.completeness+this.conclusiveness);
        }
        else if (getType() == (byte)4) {
            avg = (0.2*this.unambiguity+0.6*this.credibility+0.1*this.completeness+0.1*this.conclusiveness);
        }
        else if (getType() == (byte)3) {
            avg = (0.3*this.unambiguity+0.2*this.credibility+0.4*this.completeness+0.1*this.conclusiveness);
        }
        else if (getType() == (byte)2) {
            avg = (0.2*this.unambiguity+0.4*this.credibility+0.2*this.completeness+0.2*this.conclusiveness);
        }
        else if (getType() == (byte)1) {
            avg = (0.4*this.unambiguity+0.3*this.credibility+0.2*this.completeness+0.1*this.conclusiveness);
        }
        this.probability = (byte)avg;
        return this.probability;
    }

    public String type2String(byte type) {
        String str = "";
        if (getType() == (byte)5) {
            str = "Not Specified";
        }
        else if (getType() == (byte)4) {
            str = "Documentary Evidence";
        }
        else if (getType() == (byte)3) {
            str = "Demonstrative Evidence";
        }
        else if (getType() == (byte)2) {
            str = "Testimonial Statement";
        }
        else if (getType() == (byte)1) {
            str = "Real Evidence";
        }
        return str;
    }

    public static String probability2String(byte probability) {
        String ret;
        if (probability == (byte)5) {
            ret = "Certain";
        }
        else if (probability == (byte)4) {
            ret = "Very Likely";
        }
        else if (probability == (byte)3) {
            ret = "Likely";
        }
        else if (probability == (byte)2) {
            ret = "Unlikely";
        }
        else if (probability == (byte)1) {
            ret = "Very Unlikely";
        }
        else {
            ret = "Extremely Unlikely";
        }
        return ret;
    }
    public String toString() {
        String ret = "Evidence: " + getDescription();
        ret += "\n** Type of Evidence: " + type2String(getType());
        ret += "\n** Probability: " + computeProbability() + " -> " + probability2String(getProbability()).toLowerCase();
        return ret;
    }
    public String printFullDescription() {
        String ret = toString();
        ret += "\n** Evaluated based on those characteristics: ";
        ret += "\n** >> Unambiguity: " + getUnambiguity();
        ret += "\n** >> Credibility: " + getCredibility();
        ret += "\n** >> Completeness: " + getCompleteness();
        ret += "\n** >> Conclusiveness: " + getConclusiveness();
        return ret;
    }

}