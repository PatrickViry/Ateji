/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.dataparallelism.matrixmult;

/**
 * The purpose of this example is to compare the performance of several
 * algorithms for multiplication of matrices.
 * 
 * All algorithms are derived from the traditional method that uses three
 * nested loops.	
 * 
 * The examples will show:
 *  - the impact of the order of the three loops on performance, and
 *  - the impact of split indications on performance.
 *  
 * Split indications are the way used by Ateji PX to achieve data parallelism.
 * In the data parallelism model, each branch performs the same task on
 * different pieces of data. Data parallelism focuses on distributing 
 * the data across different parallel branches.
 */
public class MatrixMult
{
	// C[I][J] = A[I][K] * B[K][J]
	
	static final int I = 100;
	static final int J = 200;
	static final int K = 300;

	static final double[][] A;
	static {
final int freshVariable20 =I;final int freshVariable21 =K;final double [ ] [ ] freshVariable22 =new double [freshVariable20 ][freshVariable21 ];{int freshVariable24 =0;while(freshVariable24 <(freshVariable21 )){{int freshVariable23 =0;while(freshVariable23 <(freshVariable20 )){freshVariable22 [freshVariable23 ][freshVariable24 ]=Math.random();freshVariable23 ++;}}freshVariable24 ++;}}A =freshVariable22 ; }
static final double[][] B;
	static {
final int freshVariable25 =K;final int freshVariable26 =J;final double [ ] [ ] freshVariable27 =new double [freshVariable25 ][freshVariable26 ];{int freshVariable29 =0;while(freshVariable29 <(freshVariable26 )){{int freshVariable28 =0;while(freshVariable28 <(freshVariable25 )){freshVariable27 [freshVariable28 ][freshVariable29 ]=Math.random();freshVariable28 ++;}}freshVariable29 ++;}}B =freshVariable27 ; }
static final double[][] C;
	
	// The first two examples show the same algorithm to calculate the product C = A * B.
	// The first one does not use any split indication (the split is hard-coded by hand),
	// while the second one does.
	
	/**
	 * 'ijk_par_splitI' measures the algorithm when:
	 *  - the three loops are in the order 'i', 'j' and then 'k',
	 *  - the code is parallel, and
	 *  - the 'i' dimension is split.
	 */
	static {
final int freshVariable30 =I;final int freshVariable31 =J;C =new double [freshVariable30 ][freshVariable31 ]; }
static Measure ijk_par_splitI = new Measure(C) {

		@Override
		void run() {
			// Manually split the iterator I in nProcs pieces.			
			{
{
final apx . util . RangeInteger range6 = new apx . util . RangeInteger (0 , (I )- 1 ); final int blockcount11 = java . lang . Runtime .getRuntime ().availableProcessors (); { final java . util . List < apx . lang . gen . Branch > branches22 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs22 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock22 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range57 = new apx . util . RangeInteger (0 , ((blockcount11 )- 1 ));
final int last48 = (range57 ). max ;
for(int block46 = (range57 ). min ;block46 <= last48 ;block46 ++ )
{
final int nbBlock11 = block46 ;
{
{
apx . lang . gen . Branch branch35 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
{
final apx . util . RangeInteger range58 = new apx . util . RangeInteger ((((range6 ). min )+ ((((range6 ). max )- ((range6 ). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((range6 ). max )- ((range6 ). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* (nbBlock11 )), (java . lang . Math . min (((range6 ). max ), ((range6 ). min )+ ((((range6 ). max )- ((range6 ). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((range6 ). max )- ((range6 ). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* ((nbBlock11 )+ 1 )- 1 )));
final int last49 = (range58 ). max ;
for(int block47 = (range58 ). min ;block47 <= last49 ;block47 ++ )
{
final int i = block47 ;
{
{
final apx . util . RangeInteger range59 = new apx . util . RangeInteger (0 , ((com . ateji . px . dataparallelism . matrixmult . MatrixMult .J)- 1 ));
final int last50 = (range59 ). max ;
for(int block48 = (range59 ). min ;block48 <= last50 ;block48 ++ )
{
int j = block48 ;
{
{
{
				double res = 0.0;
				{
final apx . util . RangeInteger range60 = new apx . util . RangeInteger (0 , ((com . ateji . px . dataparallelism . matrixmult . MatrixMult .K)- 1 ));
final int last51 = (range60 ). max ;
for(int block49 = (range60 ). min ;block49 <= last51 ;block49 ++ )
{
int k = block49 ;
{
{
					// Formula for calculating a product of matrices.
					res += com . ateji . px . dataparallelism . matrixmult . MatrixMult .A[i][k]*com . ateji . px . dataparallelism . matrixmult . MatrixMult .B[k][j];
				}
				}
}
}
com . ateji . px . dataparallelism . matrixmult . MatrixMult .C[i][j] = res;
			}
		}
}
}
}
}
}
}
}
}
;
branches22 .add (branch35 );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus25 =parallelBlock22 . run (branches22 , bangs22 );
if(exitStatus25 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable25 =exitStatus25 .thrownException ();
if(throwable25 !=null) {
if(throwable25 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable25 ;
if(throwable25 instanceof java . lang . Error ) throw (java . lang . Error )throwable25 ;
}
}
} }
}
}

		@Override
		String name() { return "ijk_par_splitI"; }
	};

	/**
	 * 'ijk_par_splitI_indic' measures the algorithm when:
	 *  - the three loops are in the order 'i', 'j' and then 'k',
	 *  - the code is parallel,
	 *  - the 'i' dimension is split, and
	 *  - an Ateji PX split indication is used.
	 */
	static Measure ijk_par_splitI_indic = new Measure(C) {

		@Override
		void run() {			
			// This parallel for splits matrices according to the indications given in the branches. 
			// Its creates 'nProcs' parallel branches.  Each branch deals with 'I/nProcs' 
			// iterations of the loop. The rest of the code is executed sequentially 
			// in the branch.
			// ijk_par_splitI_indic and ijk_par_splitI strategies are equivalent
			{
{
final apx . util . RangeInteger range7 = new apx . util . RangeInteger (0 , (I)- 1 ); final int blockcount12 = nProcs; { final java . util . List < apx . lang . gen . Branch > branches23 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs23 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock23 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range61 = new apx . util . RangeInteger (0 , ((blockcount12 )- 1 ));
final int last52 = (range61 ). max ;
for(int block50 = (range61 ). min ;block50 <= last52 ;block50 ++ )
{
final int nbBlock12 = block50 ;
{
{
apx . lang . gen . Branch branch36 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
{
final apx . util . RangeInteger range62 = new apx . util . RangeInteger ((((range7 ). min )+ ((((range7 ). max )- ((range7 ). min )+ 1 )/ (com . ateji . px . dataparallelism . matrixmult . MatrixMult .nProcs)+ ((((range7 ). max )- ((range7 ). min )+ 1 )% (com . ateji . px . dataparallelism . matrixmult . MatrixMult .nProcs)== 0 ? 0 : 1 ))* (nbBlock12 )), (java . lang . Math . min (((range7 ). max ), ((range7 ). min )+ ((((range7 ). max )- ((range7 ). min )+ 1 )/ (com . ateji . px . dataparallelism . matrixmult . MatrixMult .nProcs)+ ((((range7 ). max )- ((range7 ). min )+ 1 )% (com . ateji . px . dataparallelism . matrixmult . MatrixMult .nProcs)== 0 ? 0 : 1 ))* ((nbBlock12 )+ 1 )- 1 )));
final int last53 = (range62 ). max ;
for(int block51 = (range62 ). min ;block51 <= last53 ;block51 ++ )
{
final int i = block51 ;
{
{
final apx . util . RangeInteger range63 = new apx . util . RangeInteger (0 , ((com . ateji . px . dataparallelism . matrixmult . MatrixMult .J)- 1 ));
final int last54 = (range63 ). max ;
for(int block52 = (range63 ). min ;block52 <= last54 ;block52 ++ )
{
int j = block52 ;
{
{
{  
			  	double res = 0.0;
			  	{
final apx . util . RangeInteger range64 = new apx . util . RangeInteger (0 , ((com . ateji . px . dataparallelism . matrixmult . MatrixMult .K)- 1 ));
final int last55 = (range64 ). max ;
for(int block53 = (range64 ). min ;block53 <= last55 ;block53 ++ )
{
int k = block53 ;
{
{
			  		// Formula for calculating a product of matrices.
			  		res += com . ateji . px . dataparallelism . matrixmult . MatrixMult .A[i][k] * com . ateji . px . dataparallelism . matrixmult . MatrixMult .B[k][j];
			  	}
			  	}
}
}
com . ateji . px . dataparallelism . matrixmult . MatrixMult .C[i][j] = res; 
			}
			
		}
}
}
}
}
}
}
}
}
;
branches23 .add (branch36 );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus26 =parallelBlock23 . run (branches23 , bangs23 );
if(exitStatus26 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable26 =exitStatus26 .thrownException ();
if(throwable26 !=null) {
if(throwable26 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable26 ;
if(throwable26 instanceof java . lang . Error ) throw (java . lang . Error )throwable26 ;
}
}
} }
}
}
		
		@Override
		String name() { return "ijk_par_splitI_indic"; }
	};
	
	
	
	// This matrix is used to verify that all the algorithms are consistent with each other.
	static double[][] check;

	static final int nProcs = Runtime.getRuntime().availableProcessors();
	
	public static void main(String[] args)
	{
		checkMatrix();
	
		System.out.println(
				"Performance measurements should take into account the JVM \"warm-up\".\n" +
				"The first two or three timings are often much larger.\n" +
				"Different runs may also give slightly different results.\n" +
				"We calculate the mean of all runs except the first three ones.\n"
		);
		ijk_seq_iter.measure();

		System.out.println(
				"\n" +
				"In particular, Java5-style iterators (1st line) get as fast as \n" +
				"classical for loops (2nd line) after some warm-up time.\n" +
				"(hence there is no need for a compile-time optimization, except possibly for memory)\n"
		);
		ijk_seq_iter.measure();
		ijk_seq_classical.measure();

		System.out.println(
				"\n" +
				"Computing intermediate results in a private variable has\n" +
				"a small impact on performance.\n"
		);
		ijk_seq_iter.measure();
		ijk_seq_iter_uncached.measure();

		System.out.println(
				"\n" +
				"Before parallelizing for performance, start with an efficient\n" +
				"sequential algorithm. The six following lines correspond to all permutations\n" +
				"of the three loops in the naive multiplication algorithm. Depending on the\n" +
				"configuration of the computer the performance can differ by a factor of 4x,\n" +
				"showing the impact of cache access.\n"
		);
				
		ijk_seq_iter.measure();
		ikj_seq.measure();
		jik_seq.measure();
		jki_seq.measure();
		kij_seq.measure();
		kji_seq.measure();
		
		System.out.println(
				"\n" +
				"Specific optimisations of these codes, typically by memoizing intermediate results,\n" +
				"can lead to even better performance\n"
		);
		jik_opt_seq.measure();
				
		System.out.println(
				"\n" +
				"Different strategies for parallelizing the ijk version:\n" +
				" - sequential (for comparison)\n" +
				" - split the i loop among the number of processors\n" +
				" - split the j loop among the number of processors\n" +
				" - make a branch for each i\n" +
				" - make a branch for each i and j\n" +
				"The split version is consistently the fastest, while making too many branches\n" +
				"generally leads to a worse than sequential performance.\n" +
				"(nProcs = " + nProcs + ")\n"
		);
		
		ijk_seq_iter.measure();		
		ijk_par_splitI.measure();
		ijk_par_splitI_indic.measure();
		ijk_par_splitJ.measure();
		jik_par_splitJ_indic.measure();
		ijk_par_I.measure();
		ijk_par_IJ.measure();


		System.out.println(
				"\n" +
				"Different strategies for parallelizing the jik version:\n" +
				" - sequential (for comparison)\n" +
				" - split the j loop among the number of processors\n" +
				" - make a branch for each j\n" +
				"Again the split version is the fastest; an almost linear speed-up\n" +
				"should be achieved.\n" +
				"(nProcs = " + nProcs + ")\n"
		);
		
		jik_opt_seq.measure();
		jik_opt_parJ.measure();
		jik_opt_par_splitJ.measure();
		
		System.out.println(
				"\n" +
				"Depending on the configuration of the computer, optimizing the algorithm over\n" +
				"the naive version can give a 10x speedup. Parallelizing this optimized version\n" +
				"by splitting should give an almost linear speedup.\n" +
				"(nProcs = " + nProcs + ")\n"
		);
		
		System.out.println("\ndone");
	}

	static void checkMatrix()
	{
		check = new double[I][J];
		{
final apx . util . RangeInteger range65 = new apx . util . RangeInteger (0 , ((I)- 1 ));
final int last56 = (range65 ). max ;
for(int block54 = (range65 ). min ;block54 <= last56 ;block54 ++ )
{
int i = block54 ;
{
{
final apx . util . RangeInteger range66 = new apx . util . RangeInteger (0 , ((J)- 1 ));
final int last57 = (range66 ). max ;
for(int block55 = (range66 ). min ;block55 <= last57 ;block55 ++ )
{
int j = block55 ;
{
{
final apx . util . RangeInteger range67 = new apx . util . RangeInteger (0 , ((K)- 1 ));
final int last58 = (range67 ). max ;
for(int block56 = (range67 ). min ;block56 <= last58 ;block56 ++ )
{
int k = block56 ;
{
{
			check[i][j] += A[i][k] * B[k][j];
		}	
	}
}
}
}
}
}
}
}
}
}
	
	/**
	 * 'ijk_seq_iter' measures the algorithm when:
	 *  - the three loops are in the order 'i', 'j' and then 'k',
	 *  - the code is sequential, and
	 *  - the code uses Java iterators.
	 */
	static Measure ijk_seq_iter = new Measure(C) {
		@Override
		String name() { return "ijk_seq_iter"; }
		@Override
		void run() {
			{
final apx . util . RangeInteger range68 = new apx . util . RangeInteger (0 , ((I)- 1 ));
final int last59 = (range68 ). max ;
for(int block57 = (range68 ). min ;block57 <= last59 ;block57 ++ )
{
int i = block57 ;
{
{
final apx . util . RangeInteger range69 = new apx . util . RangeInteger (0 , ((J)- 1 ));
final int last60 = (range69 ). max ;
for(int block58 = (range69 ). min ;block58 <= last60 ;block58 ++ )
{
int j = block58 ;
{
{
				double res = 0.0;
				{
final apx . util . RangeInteger range70 = new apx . util . RangeInteger (0 , ((K)- 1 ));
final int last61 = (range70 ). max ;
for(int block59 = (range70 ). min ;block59 <= last61 ;block59 ++ )
{
int k = block59 ;
{
{
					res += A[i][k] * B[k][j];
				}
				}
}
}
C[i][j] = res;
			}		
		}
}
}
}
}
}
}
	};

	/**
	 * 'ijk_seq_iter_uncached' measures the algorithm when:
	 *  - the three loops are in the order 'i', 'j' and then 'k',
	 *  - the code is sequential, and
	 *  - the intermediate sum is not stored in a local variable.
	 */
	static Measure ijk_seq_iter_uncached = new Measure(C) {
		@Override
		String name() { return "ijk_seq_iter_uncached"; }
		@Override
		void run() {
			{
final apx . util . RangeInteger range71 = new apx . util . RangeInteger (0 , ((I)- 1 ));
final int last62 = (range71 ). max ;
for(int block60 = (range71 ). min ;block60 <= last62 ;block60 ++ )
{
int i = block60 ;
{
{
final apx . util . RangeInteger range72 = new apx . util . RangeInteger (0 , ((J)- 1 ));
final int last63 = (range72 ). max ;
for(int block61 = (range72 ). min ;block61 <= last63 ;block61 ++ )
{
int j = block61 ;
{
{
final apx . util . RangeInteger range73 = new apx . util . RangeInteger (0 , ((K)- 1 ));
final int last64 = (range73 ). max ;
for(int block62 = (range73 ). min ;block62 <= last64 ;block62 ++ )
{
int k = block62 ;
{
{
				C[i][j] += A[i][k] * B[k][j];
			}	
		}
}
}
}
}
}
}
}
}
}
	};

	/**
	 * 'ijk_seq_classical' measures the classical, naive algorithm which is
	 * commonly found in mathematical books.
	 */
	static Measure ijk_seq_classical = new Measure(C) {
		@Override
		String name() { return "ijk_seq_classical"; }
		@Override
		void run() {
			for(int i = 0; i <  I; i++) {
				for(int j = 0; j <  J; j++) {
					double res = 0.0;
					{
final apx . util . RangeInteger range74 = new apx . util . RangeInteger (0 , ((K)- 1 ));
final int last65 = (range74 ). max ;
for(int block63 = (range74 ). min ;block63 <= last65 ;block63 ++ )
{
int k = block63 ;
{
{
						res +=  A[i][k] *  B[k][j];
					}
					}
}
}
C[i][j] = res;
				}
			}
		}
	};

	/**
	 * 'ijk_par_splitJ' measures the algorithm when:
	 *  - the three loops are in the order 'i', 'j' and then 'k',
	 *  - the code is parallel, and
	 *  - the 'j' dimension is split.
	 */
	static Measure ijk_par_splitJ = new Measure(C) {
		@Override
		String name() { return "ijk_par_splitJ"; }
		@Override
		void run() {
			{
{
			final java . util . List < apx . lang . gen . Branch > branches24 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs24 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock24 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range75 = new apx . util . RangeInteger (0 , ((com . ateji . px . dataparallelism . matrixmult . MatrixMult .nProcs)- 1 ));
final int last66 = (range75 ). max ;
for(int block64 = (range75 ). min ;block64 <= last66 ;block64 ++ )
{
final int splitJ = block64 ;
{
{
apx . lang . gen . Branch branch37 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
{
				{
final apx . util . RangeInteger range76 = new apx . util . RangeInteger (0 , ((com . ateji . px . dataparallelism . matrixmult . MatrixMult .I)- 1 ));
final int last67 = (range76 ). max ;
for(int block65 = (range76 ). min ;block65 <= last67 ;block65 ++ )
{
int i = block65 ;
{
{
final apx . util . RangeInteger range77 = new apx . util . RangeInteger (splitJ*com . ateji . px . dataparallelism . matrixmult . MatrixMult .J/ com . ateji . px . dataparallelism . matrixmult . MatrixMult .nProcs , (splitJ+1)*com . ateji . px . dataparallelism . matrixmult . MatrixMult .J/ com . ateji . px . dataparallelism . matrixmult . MatrixMult .nProcs-1);
final int last68 = (range77 ). max ;
for(int block66 = (range77 ). min ;block66 <= last68 ;block66 ++ )
{
int j = block66 ;
{
{
			 			double res = 0.0;
			 			{
final apx . util . RangeInteger range78 = new apx . util . RangeInteger (0 , ((com . ateji . px . dataparallelism . matrixmult . MatrixMult .K)- 1 ));
final int last69 = (range78 ). max ;
for(int block67 = (range78 ). min ;block67 <= last69 ;block67 ++ )
{
int k = block67 ;
{
{
			 				res +=  com . ateji . px . dataparallelism . matrixmult . MatrixMult .A[i][k] *  com . ateji . px . dataparallelism . matrixmult . MatrixMult .B[k][j];
			 			}
			 			}
}
}
com . ateji . px . dataparallelism . matrixmult . MatrixMult .C[i][j] = res;
			 	}
			}
}
}
}
}
}
}
			}
}
;
branches24 .add (branch37 );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus27 =parallelBlock24 . run (branches24 , bangs24 );
if(exitStatus27 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable27 =exitStatus27 .thrownException ();
if(throwable27 !=null) {
if(throwable27 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable27 ;
if(throwable27 instanceof java . lang . Error ) throw (java . lang . Error )throwable27 ;
}
}
}
		}
}
	};
	
	/**
	 * 'jik_par_splitJ_indic' measures the algorithm when:
	 *  - the three loops are in the order 'j', 'i' and then 'k',
	 *  - the code is parallel,
	 *  - the 'j' dimension is split, and
	 *  - an Ateji PX split indication is used.
	 */
	static Measure jik_par_splitJ_indic = new Measure(C) {
		@Override
		String name() { return "jik_par_splitJ_indic"; }
		@Override
		void run() {			
			{
{
final apx . util . RangeInteger range8 = new apx . util . RangeInteger (0 , (J)- 1 ); final int blockcount13 = java . lang . Runtime .getRuntime ().availableProcessors (); { final java . util . List < apx . lang . gen . Branch > branches25 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs25 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock25 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range79 = new apx . util . RangeInteger (0 , ((blockcount13 )- 1 ));
final int last70 = (range79 ). max ;
for(int block68 = (range79 ). min ;block68 <= last70 ;block68 ++ )
{
final int nbBlock13 = block68 ;
{
{
apx . lang . gen . Branch branch38 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
{
final apx . util . RangeInteger range80 = new apx . util . RangeInteger ((((range8 ). min )+ ((((range8 ). max )- ((range8 ). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((range8 ). max )- ((range8 ). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* (nbBlock13 )), (java . lang . Math . min (((range8 ). max ), ((range8 ). min )+ ((((range8 ). max )- ((range8 ). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((range8 ). max )- ((range8 ). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* ((nbBlock13 )+ 1 )- 1 )));
final int last71 = (range80 ). max ;
for(int block69 = (range80 ). min ;block69 <= last71 ;block69 ++ )
{
final int j = block69 ;
{
{
final apx . util . RangeInteger range81 = new apx . util . RangeInteger (0 , ((com . ateji . px . dataparallelism . matrixmult . MatrixMult .I)- 1 ));
final int last72 = (range81 ). max ;
for(int block70 = (range81 ). min ;block70 <= last72 ;block70 ++ )
{
int i = block70 ;
{
{
{
				 double res = 0.0;
				 {
final apx . util . RangeInteger range82 = new apx . util . RangeInteger (0 , ((com . ateji . px . dataparallelism . matrixmult . MatrixMult .K)- 1 ));
final int last73 = (range82 ). max ;
for(int block71 = (range82 ). min ;block71 <= last73 ;block71 ++ )
{
int k = block71 ;
{
{
					 res +=  com . ateji . px . dataparallelism . matrixmult . MatrixMult .A[i][k] *  com . ateji . px . dataparallelism . matrixmult . MatrixMult .B[k][j];
				 }
				 }
}
}
com . ateji . px . dataparallelism . matrixmult . MatrixMult .C[i][j] = res;
			}			
		}
}
}
}
}
}
}
}
}
;
branches25 .add (branch38 );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus28 =parallelBlock25 . run (branches25 , bangs25 );
if(exitStatus28 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable28 =exitStatus28 .thrownException ();
if(throwable28 !=null) {
if(throwable28 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable28 ;
if(throwable28 instanceof java . lang . Error ) throw (java . lang . Error )throwable28 ;
}
}
} }
}
}
	};

	/**
	 * 'ijk_par_I' measures the algorithm when:
	 *  - the three loops are in the order 'i', 'j' and then 'k',
	 *  - the code is parallel, and
	 *  - the 'I' dimension is parallelized.
	 */
	static Measure ijk_par_I = new Measure(C) {
		@Override
		String name() { return "ijk_par_I"; }
		@Override
		void run() {
			{
{
			final java . util . List < apx . lang . gen . Branch > branches26 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs26 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock26 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range83 = new apx . util . RangeInteger (0 , ((com . ateji . px . dataparallelism . matrixmult . MatrixMult .I)- 1 ));
final int last74 = (range83 ). max ;
for(int block72 = (range83 ). min ;block72 <= last74 ;block72 ++ )
{
final int i = block72 ;
{
{
apx . lang . gen . Branch branch39 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
{
				{
final apx . util . RangeInteger range84 = new apx . util . RangeInteger (0 , ((com . ateji . px . dataparallelism . matrixmult . MatrixMult .J)- 1 ));
final int last75 = (range84 ). max ;
for(int block73 = (range84 ). min ;block73 <= last75 ;block73 ++ )
{
int j = block73 ;
{
{
					double res = 0.0;
					{
final apx . util . RangeInteger range85 = new apx . util . RangeInteger (0 , ((com . ateji . px . dataparallelism . matrixmult . MatrixMult .K)- 1 ));
final int last76 = (range85 ). max ;
for(int block74 = (range85 ). min ;block74 <= last76 ;block74 ++ )
{
int k = block74 ;
{
{
						res +=  com . ateji . px . dataparallelism . matrixmult . MatrixMult .A[i][k] *  com . ateji . px . dataparallelism . matrixmult . MatrixMult .B[k][j];
					}
					}
}
}
com . ateji . px . dataparallelism . matrixmult . MatrixMult .C[i][j] = res;
				}
			}
}
}
}
			}
}
;
branches26 .add (branch39 );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus29 =parallelBlock26 . run (branches26 , bangs26 );
if(exitStatus29 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable29 =exitStatus29 .thrownException ();
if(throwable29 !=null) {
if(throwable29 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable29 ;
if(throwable29 instanceof java . lang . Error ) throw (java . lang . Error )throwable29 ;
}
}
}
		}
}
	};

	/**
	 * 'ijk_par_IJ' measures the algorithm when:
	 *  - the three loops are in the order 'i', 'j' and then 'k',
	 *  - the code is parallel, and
	 *  - both 'I' and 'J' dimensions are parallelized.
	 */
	static Measure ijk_par_IJ = new Measure(C) {
		@Override
		String name() { return "ijk_par_IJ"; }
		@Override
		void run() {
			{
{
			final java . util . List < apx . lang . gen . Branch > branches27 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs27 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock27 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range86 = new apx . util . RangeInteger (0 , ((com . ateji . px . dataparallelism . matrixmult . MatrixMult .I)- 1 ));
final int last77 = (range86 ). max ;
for(int block75 = (range86 ). min ;block75 <= last77 ;block75 ++ )
{
final int i = block75 ;
{
{
final apx . util . RangeInteger range87 = new apx . util . RangeInteger (0 , ((com . ateji . px . dataparallelism . matrixmult . MatrixMult .J)- 1 ));
final int last78 = (range87 ). max ;
for(int block76 = (range87 ). min ;block76 <= last78 ;block76 ++ )
{
final int j = block76 ;
{
{
apx . lang . gen . Branch branch40 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
{
				 double res = 0.0;
				 {
final apx . util . RangeInteger range88 = new apx . util . RangeInteger (0 , ((com . ateji . px . dataparallelism . matrixmult . MatrixMult .K)- 1 ));
final int last79 = (range88 ). max ;
for(int block77 = (range88 ). min ;block77 <= last79 ;block77 ++ )
{
int k = block77 ;
{
{
					 res += com . ateji . px . dataparallelism . matrixmult . MatrixMult .A[i][k] * com . ateji . px . dataparallelism . matrixmult . MatrixMult .B[k][j];
				 }
				 }
}
}
com . ateji . px . dataparallelism . matrixmult . MatrixMult .C[i][j] = res;
			}
			}
}
;
branches27 .add (branch40 );
}
}
}
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus30 =parallelBlock27 . run (branches27 , bangs27 );
if(exitStatus30 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable30 =exitStatus30 .thrownException ();
if(throwable30 !=null) {
if(throwable30 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable30 ;
if(throwable30 instanceof java . lang . Error ) throw (java . lang . Error )throwable30 ;
}
}
}
		}
}
	};
	
	/**
	 * 'ikj_seq' measures the algorithm when:
	 *  - the three loops are in the order 'i', 'j' and then 'k', and
	 *  - the code is sequential.
	 */
	static Measure ikj_seq = new Measure(C) {
		@Override
		String name() { return "ikj_seq"; }
		@Override
		void run() {
			for (int i = 0; i < I; i++) {
				for (int k = 0; k < K; k++) {
					for (int j = 0; j < J; j++) {
						 C[i][j] += A[i][k] * B[k][j];
					}
				}
			}
		}
	};

	/**
	 * 'jik_seq' measures the algorithm when:
	 *  - the three loops are in the order 'j', 'i' and then 'k', and
	 *  - the code is sequential.
	 */
	static Measure jik_seq = new Measure(C) {
		@Override
		String name() { return "jik_seq"; }
		@Override
		void run() {
			for (int j = 0; j < J; j++) {
				for (int i = 0; i < I; i++) {
					for (int k = 0; k < K; k++) {
						C[i][j] += A[i][k] * B[k][j];
					}
				}
			}
		}
	};

	/**
	 * 'jki_seq' measures the algorithm when:
	 *  - the three loops are in the order 'j', 'k' and then 'i', and
	 *  - the code is sequential.
	 */
	static Measure jki_seq = new Measure(C) {
		@Override
		String name() { return "jki_seq"; }
		@Override
		void run() {
			for (int j = 0; j < J; j++) {
				for (int k = 0; k < K; k++) {
					for (int i = 0; i < I; i++) {
						C[i][j] += A[i][k] * B[k][j];
					}
				}
			}
		}
	};

	/**
	 * 'kij_seq' measures the algorithm when:
	 *  - the three loops are in the order 'k', 'i' and then 'j', and
	 *  - the code is sequential.
	 */
	static Measure kij_seq = new Measure(C) {
		@Override
		String name() { return "kij_seq"; }
		@Override
		void run() {
			for (int k = 0; k < K; k++) {
				for (int i = 0; i < I; i++) {
					for (int j = 0; j < J; j++) {
						C[i][j] += A[i][k] * B[k][j];
					}
				}
			}
		}
	};

	/**
	 * 'kji_seq' measures the algorithm when:
	 *  - the three loops are in the order 'k', 'j' and then 'i', and
	 *  - the code is sequential.
	 */
	static Measure kji_seq = new Measure(C) {
		@Override
		String name() { return "kji_seq"; }
		@Override
		void run() {
			for (int k = 0; k < K; k++) {
				for (int j = 0; j < J; j++) {
					for (int i = 0; i < I; i++) {
						C[i][j] += A[i][k] * B[k][j];
					}
				}
			}
		}
	};

	/**
	 * 'jik_opt_seq' measures the algorithm when:
	 *  - the three loops are in the order 'j', 'i' and then 'k',
	 *  - the code is sequential, and
	 *  - the algorithm is optimized.
	 */
	static Measure jik_opt_seq = new Measure(C) {
		@Override
		String name() { return "jik_opt_seq"; }
		@Override
		void run() {
			double[] bcolj = new double[K];
			for (int j = 0; j < J; j++) {
				for (int k = 0; k < K; k++) bcolj[k] = B[k][j];
				for (int i = 0; i < I; i++) {
					double[] arowi = A[i];
					double sum = 0.0;
					for (int k = 0; k < K; k++) {
						sum += arowi[k] * bcolj[k];
					}
					C[i][j] = sum;
				}
			}
		}
	};

	/**
	 * 'jik_opt_parJ' measures the algorithm when:
	 *  - the three loops are in the order 'j', 'i' and then 'k',
	 *  - the code is parallel,
	 *  - the 'J' dimension is parallelized, and
	 *  - the algorithm is optimized.
	 */
	static Measure jik_opt_parJ = new Measure(C) {
		@Override
		String name() { return "jik_opt_parJ"; }
		@Override
		void run() {
			{
{
			final java . util . List < apx . lang . gen . Branch > branches28 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs28 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock28 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range89 = new apx . util . RangeInteger (0 , ((com . ateji . px . dataparallelism . matrixmult . MatrixMult .J)- 1 ));
final int last80 = (range89 ). max ;
for(int block78 = (range89 ). min ;block78 <= last80 ;block78 ++ )
{
final int j = block78 ;
{
{
apx . lang . gen . Branch branch41 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
{
				double[] bcolj = new double[com . ateji . px . dataparallelism . matrixmult . MatrixMult .K];
				for (int k = 0; k < com . ateji . px . dataparallelism . matrixmult . MatrixMult .K; k++) {
					bcolj[k] = com . ateji . px . dataparallelism . matrixmult . MatrixMult .B[k][j];
				}
				
				for (int i = 0; i < com . ateji . px . dataparallelism . matrixmult . MatrixMult .I; i++) {
					double[] arowi = com . ateji . px . dataparallelism . matrixmult . MatrixMult .A[i];
					double sum = 0.0;
					for (int k = 0; k < com . ateji . px . dataparallelism . matrixmult . MatrixMult .K; k++) {
						sum += arowi[k] * bcolj[k];
					}
					com . ateji . px . dataparallelism . matrixmult . MatrixMult .C[i][j] = sum;
				}
			}
			}
}
;
branches28 .add (branch41 );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus31 =parallelBlock28 . run (branches28 , bangs28 );
if(exitStatus31 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable31 =exitStatus31 .thrownException ();
if(throwable31 !=null) {
if(throwable31 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable31 ;
if(throwable31 instanceof java . lang . Error ) throw (java . lang . Error )throwable31 ;
}
}
}
		}
}
	};

	/**
	 * 'jik_opt_parJ' measures the algorithm when:
	 *  - the three loops are in the order 'j', 'i' and then 'k',
	 *  - the code is parallel,
	 *  - the 'J' dimension is parallelized,
	 *  - a Ateji PX split indication is used, and
	 *  - the algorithm is optimized.
	 */
	static Measure jik_opt_par_splitJ = new Measure(C) {
		@Override
		String name() { return "jik_opt_par_splitJ"; }
		@Override
		void run() {
			{
{
final apx . util . RangeInteger range9 = new apx . util . RangeInteger (0 , (J)- 1 ); final int blockcount14 = java . lang . Runtime .getRuntime ().availableProcessors (); { final java . util . List < apx . lang . gen . Branch > branches29 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs29 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock29 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range90 = new apx . util . RangeInteger (0 , ((blockcount14 )- 1 ));
final int last81 = (range90 ). max ;
for(int block79 = (range90 ). min ;block79 <= last81 ;block79 ++ )
{
final int nbBlock14 = block79 ;
{
{
apx . lang . gen . Branch branch42 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
{
final apx . util . RangeInteger range91 = new apx . util . RangeInteger ((((range9 ). min )+ ((((range9 ). max )- ((range9 ). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((range9 ). max )- ((range9 ). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* (nbBlock14 )), (java . lang . Math . min (((range9 ). max ), ((range9 ). min )+ ((((range9 ). max )- ((range9 ). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((range9 ). max )- ((range9 ). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* ((nbBlock14 )+ 1 )- 1 )));
final int last82 = (range91 ). max ;
for(int block80 = (range91 ). min ;block80 <= last82 ;block80 ++ )
{
final int j = block80 ;
{
{
{
					double[] bcolj = new double[com . ateji . px . dataparallelism . matrixmult . MatrixMult .K];
					for (int k = 0; k < com . ateji . px . dataparallelism . matrixmult . MatrixMult .K; k++) {
						bcolj[k] = com . ateji . px . dataparallelism . matrixmult . MatrixMult .B[k][j];
					}
					
					for (int i = 0; i < com . ateji . px . dataparallelism . matrixmult . MatrixMult .I; i++) {
						double[] arowi =  com . ateji . px . dataparallelism . matrixmult . MatrixMult .A[i];
						double sum = 0.0;
						for (int k = 0; k < com . ateji . px . dataparallelism . matrixmult . MatrixMult .K; k++) {
							sum += arowi[k] * bcolj[k];
						}
						com . ateji . px . dataparallelism . matrixmult . MatrixMult .C[i][j] = sum;
					}
				}
			}
}
}
}
}
}
;
branches29 .add (branch42 );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus32 =parallelBlock29 . run (branches29 , bangs29 );
if(exitStatus32 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable32 =exitStatus32 .thrownException ();
if(throwable32 !=null) {
if(throwable32 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable32 ;
if(throwable32 instanceof java . lang . Error ) throw (java . lang . Error )throwable32 ;
}
}
} }
}
}
	};

}
