public class Reasoning {
	
	public static void main(String[] args) {
		
		//setting the scope for Test 1
		{
			System.out.println("---TEST-1---");
			Problem prob = new Problem("Is Jupyter producing Nuclear Bomb?");
			System.out.println(prob.generateArgumentation());
		}
		
		//setting the scope for Test 2
		{
			System.out.println("---TEST-2---");
			Hypothesis rootHypothesis = new Hypothesis("Jupyter is producting Nuclear Boomb.");
			System.out.println(rootHypothesis);
		}
		
		//setting the scope for Test 3
		{
			System.out.println("---TEST-3---");
			Problem prob = new Problem("Is Jupyter producing Nuclear Bomb?");
			Hypothesis rootHypothesis = new Hypothesis("Jupyter is producting Nuclear Boomb.");
			prob.rootHypothesis = rootHypothesis;
			System.out.println(prob.generateArgumentation());
		}
		
		//setting the scope for Test 4
		{
			System.out.println("---TEST-4---");
			Evidence claim1 = new Evidence("Jupyter does have scientist. (GMU claim)", (byte)1, (byte)5, (byte)3, (byte)4, (byte)2);
			System.out.println(claim1);
		}
		
		//Setting the scope for test 5
		{
			System.out.println("---TEST-5---");
			Evidence claim1 = new Evidence("Jupyter does have scientist. (GMU claim)", (byte)1, (byte)5, (byte)3, (byte)4, (byte)2);
			System.out.println(claim1.printFullDescription());	
		}
		
		//Setting the scope for test 6
		{
			System.out.println("---TEST-6---");
			Evidence claim1 = new Evidence("Jupyter does have scientist. (GMU claim)", (byte)1, (byte)5, (byte)3, (byte)4, (byte)2);
			System.out.println(claim1.printFullDescription());
			Relevance relevance1 = new Relevance(claim1, (byte)3);
			System.out.println(relevance1);		
		}
		
		//Setting the scope for test 7
		{
			System.out.println("---TEST-7---");
			Problem prob = new Problem("Is Jupyter producing Nuclear Bomb?");
			Hypothesis rootHypothesis = new Hypothesis("Jupyter is producting Nuclear Boomb.");
			prob.rootHypothesis = rootHypothesis;		
			
			Evidence claim1 = new Evidence("Jupyter does have scientist. (GMU claim)", (byte)1, (byte)5, (byte)3, (byte)4, (byte)2);
			Relevance relevance1 = new Relevance(claim1, (byte)3);
			rootHypothesis.addSupportingItem(relevance1);
			
			Hypothesis subHypothesis1 = new Hypothesis("Jupyter has enough space to develop weapons.");
			subHypothesis1.setProbability((byte)5);
			Relevance relevance2 = new Relevance(subHypothesis1, (byte)3);
			rootHypothesis.addSupportingItem(relevance2);
			
			Hypothesis subHypothesis2 = new Hypothesis("Jupyter has no legal constraint.");
			subHypothesis2.setProbability((byte)5);
			Relevance relevance3 = new Relevance(subHypothesis2, (byte)1);
			rootHypothesis.addSupportingItem(relevance3);
			
			rootHypothesis.setProbability();
			
			System.out.println(prob.generateArgumentation());
		}
	}
}