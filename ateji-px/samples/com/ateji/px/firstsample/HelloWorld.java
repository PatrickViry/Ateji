/****************************
 * Copyright (c) 2010 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.firstsample;

/**
 * If you already know the Java language, you'll only need to learn a few additional 
 * constructs to write parallel programs. The first one is the parallel composition.
 *
 * The square brackets <i>[]</i> introduce a parallel block. A parallel block is an 
 * indication given by the programmer that the branches it contains may
 * be run in parallel. Whatever the actual implementation, the set of possible program 
 * outcomes will remain the same.
 * 
 * Each || ... inside the block introduces a branch. The parallel block terminates when 
 * all branches it contains have terminated. Parallelism in Ateji PX is 
 * compositional: you can think of [ || ... || ... ] as a parallel composition operator,
 * the counterpart of the sequential composition operator { ...; ...; }.
 *
 * Running this program will print either <i>Hello World</i> or <i>World Hello</i>
 * The two branches are run in parallel, in no particular order. When multiple processors 
 * or processor cores are available, Ateji PX tries to maximize performance by allocating 
 * branches to available processing resources. Parallel blocks do not guarantee any kind 
 * of fairness in the execution of branches. In particular, branches may possibly be run 
 * in sequence one after the other in any order.
 * 
 */
public class HelloWorld 
{

  public static void main(String[] args) 
  {
    {
{
      final java . util . List < apx . lang . gen . Branch > branches14 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs14 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock14 =apx . lang . gen . Parallel .getParallelBlock (null);
{
apx . lang . gen . Branch branch19 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
System.out.println("Hello");
      }
}
;
branches14 .add (branch19 );
}
{
apx . lang . gen . Branch branch20 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
System.out.println("World");
    }
}
;
branches14 .add (branch20 );
}
final apx . lang . gen . ExitStatus exitStatus17 =parallelBlock14 . run (branches14 , bangs14 );
if(exitStatus17 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable17 =exitStatus17 .thrownException ();
if(throwable17 !=null) {
if(throwable17 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable17 ;
if(throwable17 instanceof java . lang . Error ) throw (java . lang . Error )throwable17 ;
}
}
}
  }
}

}