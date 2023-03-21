public class Problem
{
    // Fields
    public String question;
    public Hypothesis rootHypothesis;

    // Constructors
    public Problem(String question, Hypothesis rootHypothesis) {
        this.question = question;
        this.rootHypothesis = rootHypothesis;
    }
    public Problem(String question) {
        this.question = question;
        this.rootHypothesis = null;
    }
    
    //Method
    public String generateArgumentation() {
        String ret = "";
        if (this.rootHypothesis == null || this.rootHypothesis.getSupportingItems() == null) {
            ret = "";
        }
        else {
            ret = "Auto-generated report:";
            ret += "\n\tQuestion: " + this.question;
            ret += "\n\tIt is " + Evidence.probability2String(this.rootHypothesis.getProbability()).toUpperCase() + " to observe " + this.rootHypothesis.getDescription();
            ret += "\n\tThis conclusion is based on the following hypothesis or evidence:";
            for (int i = 0; i < this.rootHypothesis.getSupportingItems().length; ++i) {
                if (this.rootHypothesis.getSupportingItems()[i].getEvidence() != null) {
                    Evidence newEvidence = this.rootHypothesis.getSupportingItems()[i].getEvidence(); // gets evidence
                    ret += "\n\t\tIt is " + Evidence.probability2String(newEvidence.getProbability()).toUpperCase() + " that " + newEvidence.getDescription();
                }
                else if (this.rootHypothesis.getSupportingItems()[i].getSubHypothesis() != null) {
                    Hypothesis newHypothesis = this.rootHypothesis.getSupportingItems()[i].getSubHypothesis(); // gets sub-hypothesis
                    ret += "\n\t\tIt is " + Evidence.probability2String(newHypothesis.getProbability()).toUpperCase() + " that " + newHypothesis.getDescription();
                }
            }
        }
        
        return ret;
    }
}
