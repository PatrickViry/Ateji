/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.comprehension.exercises;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import apx.util.monoid.Monoids;

public class Solutions {

	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{		
		// Computes the sum of all elements in the given data.	   
		int toBeReturned15 = 0 ; for ( int d : data) {
toBeReturned15 += d; }
int sum = toBeReturned15 ;
		System.out.println("sum = " + sum);
		final java . lang . Object lock2 = new java . lang . Object ();
int global2 = 0 ;
final int [ ] array2 = data; final int blockcount2 = java . lang . Runtime .getRuntime ().availableProcessors (); {
final apx . lang . gen . MutableReferenceInt global20 = new apx . lang . gen . MutableReferenceInt (global2 );
{
final java . util . List < apx . lang . gen . Branch > branches10 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs10 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock10 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range41 = new apx . util . RangeInteger (0 , ((blockcount2 )- 1 ));
final int last32 = (range41 ). max ;
for(int block30 = (range41 ). min ;block30 <= last32 ;block30 ++ )
{
final int nbBlock2 = block30 ;
{
{
apx . lang . gen . Branch branch15 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
int toBeReturned16 = 0 ; {
final apx . util . RangeInteger range42 = new apx . util . RangeInteger ((((new apx . util . RangeInteger ((0 ), ((array2 . length )- 1 ))). min )+ ((((new apx . util . RangeInteger ((0 ), ((array2 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array2 . length )- 1 ))). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((new apx . util . RangeInteger ((0 ), ((array2 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array2 . length )- 1 ))). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* (nbBlock2 )), (java . lang . Math . min (((new apx . util . RangeInteger ((0 ), ((array2 . length )- 1 ))). max ), ((new apx . util . RangeInteger ((0 ), ((array2 . length )- 1 ))). min )+ ((((new apx . util . RangeInteger ((0 ), ((array2 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array2 . length )- 1 ))). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((new apx . util . RangeInteger ((0 ), ((array2 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array2 . length )- 1 ))). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* ((nbBlock2 )+ 1 )- 1 )));
final int last33 = (range42 ). max ;
for(int block31 = (range42 ). min ;block31 <= last33 ;block31 ++ )
{
final int arrayIndex1 = block31 ;
{
{ int d = array2 [ arrayIndex1 ] ; toBeReturned16 += (d); } }
}
}
final int local2 = toBeReturned16 ;
synchronized ( lock2 ) { global20 .ref += local2 ; } }
}
;
branches10 .add (branch15 );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus13 =parallelBlock10 . run (branches10 , bangs10 );
global2 = global20 .ref ;
if(exitStatus13 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable13 =exitStatus13 .thrownException ();
if(throwable13 !=null) {
if(throwable13 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable13 ;
if(throwable13 instanceof java . lang . Error ) throw (java . lang . Error )throwable13 ;
}
}
}
}
int parallelSum = global2 ;
		System.out.println("parallelSum = " + parallelSum);

		// Filter the element in the given data that are more than 10
		apx . util . monoid . Monoids . HashSetMonoid < java . lang . Integer > monoid25 = Monoids.hashSet() ; java . util . HashSet < java . lang . Integer > toBeReturned17 = monoid25 . zero ( ) ; for ( int d : data) {
if ( ( d >= 10)) { monoid25 . mutableAdd ( toBeReturned17 , d) ; } }
Set<Integer> flat = toBeReturned17 ;
		System.out.println("flat = " + flat);
		final java . lang . Object lock3 = new java . lang . Object ();
final apx . util . monoid . Monoids . HashSetMonoid < java . lang . Integer > monoid3 = Monoids.hashSet() ;
java . util . HashSet < java . lang . Integer > global3 = monoid3 . zero ( ) ;
final int [ ] array3 = data; final int blockcount3 = java . lang . Runtime .getRuntime ().availableProcessors (); {
final apx . lang . gen . MutableReference < java . util . HashSet < java . lang . Integer > > global30 = new apx . lang . gen . MutableReference < java . util . HashSet < java . lang . Integer > > (global3 );
{
final java . util . List < apx . lang . gen . Branch > branches11 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs11 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock11 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range43 = new apx . util . RangeInteger (0 , ((blockcount3 )- 1 ));
final int last34 = (range43 ). max ;
for(int block32 = (range43 ). min ;block32 <= last34 ;block32 ++ )
{
final int nbBlock3 = block32 ;
{
{
apx . lang . gen . Branch branch16 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
apx . util . monoid . Monoids . HashSetMonoid < java . lang . Integer > monoid26 = monoid3 ; java . util . HashSet < java . lang . Integer > toBeReturned18 = monoid26 . zero ( ) ; {
final apx . util . RangeInteger range44 = new apx . util . RangeInteger ((((new apx . util . RangeInteger ((0 ), ((array3 . length )- 1 ))). min )+ ((((new apx . util . RangeInteger ((0 ), ((array3 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array3 . length )- 1 ))). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((new apx . util . RangeInteger ((0 ), ((array3 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array3 . length )- 1 ))). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* (nbBlock3 )), (java . lang . Math . min (((new apx . util . RangeInteger ((0 ), ((array3 . length )- 1 ))). max ), ((new apx . util . RangeInteger ((0 ), ((array3 . length )- 1 ))). min )+ ((((new apx . util . RangeInteger ((0 ), ((array3 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array3 . length )- 1 ))). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((new apx . util . RangeInteger ((0 ), ((array3 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array3 . length )- 1 ))). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* ((nbBlock3 )+ 1 )- 1 )));
final int last35 = (range44 ). max ;
for(int block33 = (range44 ). min ;block33 <= last35 ;block33 ++ )
{
final int arrayIndex2 = block33 ;
{
{ int d = array3 [ arrayIndex2 ] ; if ( ( d >= 10)) { monoid26 . mutableAdd ( toBeReturned18 , (d)) ; } } }
}
}
final java . util . HashSet < java . lang . Integer > local3 = toBeReturned18 ;
synchronized ( lock3 ) { monoid3 . mutableMerge ( global30 .ref , local3 ) ; } }
}
;
branches11 .add (branch16 );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus14 =parallelBlock11 . run (branches11 , bangs11 );
global3 = global30 .ref ;
if(exitStatus14 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable14 =exitStatus14 .thrownException ();
if(throwable14 !=null) {
if(throwable14 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable14 ;
if(throwable14 instanceof java . lang . Error ) throw (java . lang . Error )throwable14 ;
}
}
}
}
Set<Integer> parallelFlat = global3 ;
		System.out.println("parallelFlat = " + parallelFlat);

		// Count of elements in the given data that are equal to 100.
		int toBeReturned19 = 0 ; for ( int d : data) {
if ( (d == 100) ) { toBeReturned19 += 1; } }
int count = toBeReturned19 ;
		System.out.println("count = " + count);
		int toBeReturned20 = 0 ; for ( int d : data) {
if ( (d == 100)) { toBeReturned20 += 1; } }
int parallelCount = toBeReturned20 ;
		System.out.println("parallelCount = " + parallelCount);

		// some is true iff *some* element of the data is true
		boolean toBeReturned21 = false ; for ( boolean x : booleanData) {
toBeReturned21 |= x; }
boolean some = toBeReturned21 ;
		System.out.println("some = " + some);
		final java . lang . Object lock4 = new java . lang . Object ();
boolean global4 = false ;
final boolean [ ] array4 = booleanData; final int blockcount4 = java . lang . Runtime .getRuntime ().availableProcessors (); {
final apx . lang . gen . MutableReferenceBoolean global40 = new apx . lang . gen . MutableReferenceBoolean (global4 );
{
final java . util . List < apx . lang . gen . Branch > branches12 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs12 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock12 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range45 = new apx . util . RangeInteger (0 , ((blockcount4 )- 1 ));
final int last36 = (range45 ). max ;
for(int block34 = (range45 ). min ;block34 <= last36 ;block34 ++ )
{
final int nbBlock4 = block34 ;
{
{
apx . lang . gen . Branch branch17 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
boolean toBeReturned22 = false ; {
final apx . util . RangeInteger range46 = new apx . util . RangeInteger ((((new apx . util . RangeInteger ((0 ), ((array4 . length )- 1 ))). min )+ ((((new apx . util . RangeInteger ((0 ), ((array4 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array4 . length )- 1 ))). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((new apx . util . RangeInteger ((0 ), ((array4 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array4 . length )- 1 ))). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* (nbBlock4 )), (java . lang . Math . min (((new apx . util . RangeInteger ((0 ), ((array4 . length )- 1 ))). max ), ((new apx . util . RangeInteger ((0 ), ((array4 . length )- 1 ))). min )+ ((((new apx . util . RangeInteger ((0 ), ((array4 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array4 . length )- 1 ))). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((new apx . util . RangeInteger ((0 ), ((array4 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array4 . length )- 1 ))). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* ((nbBlock4 )+ 1 )- 1 )));
final int last37 = (range46 ). max ;
for(int block35 = (range46 ). min ;block35 <= last37 ;block35 ++ )
{
final int arrayIndex3 = block35 ;
{
{ boolean x = array4 [ arrayIndex3 ] ; toBeReturned22 |= (x); } }
}
}
final boolean local4 = toBeReturned22 ;
synchronized ( lock4 ) { global40 .ref |= local4 ; } }
}
;
branches12 .add (branch17 );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus15 =parallelBlock12 . run (branches12 , bangs12 );
global4 = global40 .ref ;
if(exitStatus15 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable15 =exitStatus15 .thrownException ();
if(throwable15 !=null) {
if(throwable15 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable15 ;
if(throwable15 instanceof java . lang . Error ) throw (java . lang . Error )throwable15 ;
}
}
}
}
boolean parallelSome = global4 ;
		System.out.println("parallelSome = " + parallelSome);

		// Computes the minimum of all elements in the given data.
		apx . util . monoid . MinMonoid monoid31 = Monoids.min() ; int toBeReturned23 = monoid31 . intZero ( ) ; for ( int x : data) {
toBeReturned23 = monoid31 . intMerge ( toBeReturned23 , x) ; }
Integer min = toBeReturned23 ;
		System.out.println("parallelMin = " + min);
		final java . lang . Object lock5 = new java . lang . Object ();
final apx . util . monoid . MinMonoid monoid5 = Monoids.min() ;
int global5 = monoid5 . intZero ( ) ;
final int [ ] array5 = data ; final int blockcount5 = java . lang . Runtime .getRuntime ().availableProcessors (); {
final apx . lang . gen . MutableReferenceInt global50 = new apx . lang . gen . MutableReferenceInt (global5 );
{
final java . util . List < apx . lang . gen . Branch > branches13 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs13 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock13 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range47 = new apx . util . RangeInteger (0 , ((blockcount5 )- 1 ));
final int last38 = (range47 ). max ;
for(int block36 = (range47 ). min ;block36 <= last38 ;block36 ++ )
{
final int nbBlock5 = block36 ;
{
{
apx . lang . gen . Branch branch18 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
apx . util . monoid . MinMonoid monoid32 = monoid5 ; int toBeReturned24 = monoid32 . intZero ( ) ; {
final apx . util . RangeInteger range48 = new apx . util . RangeInteger ((((new apx . util . RangeInteger ((0 ), ((array5 . length )- 1 ))). min )+ ((((new apx . util . RangeInteger ((0 ), ((array5 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array5 . length )- 1 ))). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((new apx . util . RangeInteger ((0 ), ((array5 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array5 . length )- 1 ))). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* (nbBlock5 )), (java . lang . Math . min (((new apx . util . RangeInteger ((0 ), ((array5 . length )- 1 ))). max ), ((new apx . util . RangeInteger ((0 ), ((array5 . length )- 1 ))). min )+ ((((new apx . util . RangeInteger ((0 ), ((array5 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array5 . length )- 1 ))). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((new apx . util . RangeInteger ((0 ), ((array5 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array5 . length )- 1 ))). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* ((nbBlock5 )+ 1 )- 1 )));
final int last39 = (range48 ). max ;
for(int block37 = (range48 ). min ;block37 <= last39 ;block37 ++ )
{
final int arrayIndex4 = block37 ;
{
{ int x = array5 [ arrayIndex4 ] ; toBeReturned24 = monoid32 . intMerge ( toBeReturned24 , (x )) ; } }
}
}
final int local5 = toBeReturned24 ;
synchronized ( lock5 ) { global50 .ref = monoid5 . intMerge ( global50 .ref , local5 ) ; } }
}
;
branches13 .add (branch18 );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus16 =parallelBlock13 . run (branches13 , bangs13 );
global5 = global50 .ref ;
if(exitStatus16 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable16 =exitStatus16 .thrownException ();
if(throwable16 !=null) {
if(throwable16 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable16 ;
if(throwable16 instanceof java . lang . Error ) throw (java . lang . Error )throwable16 ;
}
}
}
}
Integer parallelMin = global5 ;
		System.out.println("parallelMin = " + parallelMin);
		
		// Filter the value in the given map that are more than 10.
		apx . util . monoid . Monoids . HashSetMonoid < java . lang . Integer > monoid33 = Monoids.hashSet() ; java . util . HashSet < java . lang . Integer > toBeReturned25 = monoid33 . zero ( ) ; for ( int v : map.values()) {
if ( (v>=10)) { monoid33 . mutableAdd ( toBeReturned25 , v) ; } }
Set<Integer> otherFilterMap = toBeReturned25 ;
		System.out.println("otherFilterMap = " + otherFilterMap);
		
	}

	public static int[] data;
	static {
final int [ ] freshVariable5 =new int [1000];{int freshVariable6 =0;for(int i : new apx . util . RangeInteger (0 , ((1000)- 1 ))){freshVariable5 [freshVariable6 ]=(int)Math.round(100*Math.sin(i));freshVariable6 ++;}}data =freshVariable5 ; }
public static int[] keys = data;
	public static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	static {
		for ( int key : keys) {
map.put(key, Math.abs(key));
	}
}
	public static boolean[] booleanData;
static {
final boolean [ ] freshVariable7 =new boolean [1000];{int freshVariable8 =0;for(int i : new apx . util . RangeInteger (0 , ((1000)- 1 ))){freshVariable7 [freshVariable8 ]=(i%19 == 1);freshVariable8 ++;}}booleanData =freshVariable7 ; }
}

