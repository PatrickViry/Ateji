package com.ateji.px.selection;

import apx.lang.InputStreamChan;

/**
 * Selection provides a way to bind the behaviour of a program to external events,
 * by committing atomically to only one message transmission operation when several
 * channels can simultaneously become ready for communication.
 * 
 * In this example, the external events occur when the user press keys on the keyboard.
 * 
 * The example take the form of a game. A player must copy a word suggested by the computer 
 * in a limited time.
 */
public class Main {
	
	public static void main(String[] args) {
		InputStreamChan<String> wordChan = new InputStreamChan<String>(System.in, new StringSerializer());
		int timeLevel = 5000; // ms 
		while (true) {
			System.out.println("try to copy the following word in less than "+timeLevel+" milliseconds.");
			String playWith = playWith();
			System.out.println(playWith);
			{
final apx . lang . InputStreamChan < java . lang . String > wordChan0 = wordChan ;
final apx . lang . gen . MutableReferenceInt timeLevel0 = new apx . lang . gen . MutableReferenceInt (timeLevel );
final java . lang . String playWith0 = playWith ;
{		     
			 final java . util . List < apx . lang . gen . Branch > branches30 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs30 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock30 =apx . lang . gen . Parallel .getParallelBlock (null);
{
apx . lang . gen . Branch branch43 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
{
final apx . lang . InputStreamChan < java . lang . String > wordChan1 = wordChan0 ;
final apx . lang . gen . MutableReferenceInt timeLevel1 = new apx . lang . gen . MutableReferenceInt (timeLevel0 .ref );
final java . lang . String playWith1 = playWith0 ;
{
			// case 1 : the player submit a word and the program checks this proposition	 
			 final java . util . List < apx . lang . gen . Choice < ? > > choices2 =new java . util . ArrayList < apx . lang . gen . Choice < ? > > ();
{
choices2 .add (new apx . lang . gen . Choice < java . lang . String > (null, wordChan1 ){
public @java .lang .Override void run () throws java . lang . Throwable {
String word =this.value ();
if (playWith1.equals(word)){
					 timeLevel1 .ref -= 100;
					 System.out.println("Right");
				 }
				 else {
					 timeLevel1 .ref += 100;
					 System.out.println("Wrong");
				 }
			 // case 2 : the player was too slow and it did not submit any word.
			 }
}
);
}
final apx . lang . gen . Timeout timeout =new apx . lang . gen . Timeout (null, (timeLevel1.ref ) ){
public @java .lang .Override void run () throws java . lang . Throwable {
{
				 System.out.println("end of the game"); 			       		        
			 	 throw new apx . lang . gen . ReturnException (); // Non local exit. See the speculative parallelism sample for more details.
			 }
			 }
}
;
final apx . lang . gen . ExitStatus exitStatus33 =apx . lang . gen . Select .select (null, choices2 , timeout );
timeLevel0 .ref = timeLevel1 .ref ;
if(exitStatus33 .hasReturned ()) {
throw new apx . lang . gen . ReturnException ();
} {
final java . lang . Throwable throwable33 =exitStatus33 .thrownException ();
if(throwable33 !=null) {
if(throwable33 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable33 ;
if(throwable33 instanceof java . lang . Error ) throw (java . lang . Error )throwable33 ;
}
}
}
			 }
}
}
;
branches30 .add (branch43 );
}
final apx . lang . gen . ExitStatus exitStatus34 =parallelBlock30 . run (branches30 , bangs30 );
timeLevel = timeLevel0 .ref ;
if(exitStatus34 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable34 =exitStatus34 .thrownException ();
if(throwable34 !=null) {
if(throwable34 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable34 ;
if(throwable34 instanceof java . lang . Error ) throw (java . lang . Error )throwable34 ;
}
}
}
		}
}		
	}
	
	public static String playWith() {
		String[] words = {"world", "wide", "words", "more", "than", "pages", "on", "the", 
				"origins", "history", "evolution", "and", "idiosyncrasies", "of",
				"the", "english", "language", "worldwide", "new", "and", "words", "in"};
		double r = Math.random();
		int index = (int)Math.round(r*(words.length-1));		
		return words[index];
	}


}
