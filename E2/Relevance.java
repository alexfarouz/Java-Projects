public class Relevance 
{
    private Evidence evidence;
    private Hypothesis subHypothesis;
    private byte level;
    private byte inferentialForce;

    // Getters
    public Evidence getEvidence() {
        return this.evidence;
    }
    public Hypothesis getSubHypothesis() {
        return this.subHypothesis;
    }
    public byte getLevel() {
        return this.level;
    }
    public byte getInferentialForce() {
        return this.inferentialForce;
    }

    // Setters
    public void setEvidence(Evidence evidence) {
        this.evidence = evidence;
        this.subHypothesis = null;
        setInferentialForce();
    }
    public void setSubHypothesis(Hypothesis subHypothesis) {
        this.evidence = null;
        this.subHypothesis = subHypothesis;
        setInferentialForce();
    }
    public void setLevel(byte level) {
        if (level >= 0 && level <= 5) {
            this.level = level;
        }
        else if (level < 0) {
            this.level = 0;
        }
        else {
            this.level = 5;
        }
    }
    public void setInferentialForce() {
        if (this.evidence != null) {
            this.inferentialForce = (byte) Math.min(this.evidence.computeProbability(), getLevel());
        }
        else if (this.subHypothesis != null) {
            this.inferentialForce = (byte) Math.min(this.subHypothesis.computeProbability(), getLevel());
        }
    }

    // Constructors
    public Relevance(Evidence evidence, byte level) {
        this.evidence = evidence;
        this.level = level;
        setInferentialForce();
    }
    public Relevance(Hypothesis subHypothesis, byte level) {
        this.subHypothesis = subHypothesis;
        this.level = level;
        setInferentialForce();
    }

    // Methods
    public byte computeInferentialForce() {
        setInferentialForce();
        return this.inferentialForce;
    }
    public String toString() {
        String ret = "Relevance";
        if (this.evidence != null) {
            ret += "\n** of: " + getEvidence().getDescription();
        }
        else if (this.subHypothesis != null) {
            ret += "\n** of: " + getSubHypothesis().getDescription();
        }
        ret += "\n** relevance level: " + getLevel() + " -> " + Evidence.probability2String(getLevel()).toLowerCase();
        ret += "\n** inferential force: " + getInferentialForce() + " -> " + Evidence.probability2String(getInferentialForce()).toLowerCase();
        return ret;
    }
}
