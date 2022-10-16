/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.comprehension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import apx.util.monoid.*;

/**
 * If you are not familiar with Ateji PX comprehensions, please refer to the Basics sample.
 * 
 * The purpose of this example is to show how to define new monoids.
 * 
 * In the Basics example, the predefined monoids and those from the library have been 
 * introduced. This example shows how to define an arbitrary monoid.
 * 
 * In this sample, the new monoid is used to compute a set of integers. It is equivalent to 
 * the code in the library for Monoids.<String>hashSet() Monoid.
 */
public class UserDefinedMonoid 
{
	
	/**
	 * Shows various ways to define a monoid that builds sets of String.
	 */
	public static void main(String[] args) {

		// The purpose of this example is to explain how to build your own monoid.
		// The new monoid will build sets of String.
		//
		// This example will do a tour of all the different monoids that are available
		// in Ateji PX:
		// 1. By extending the Monoid class.
		// 2. By extending the MutableMonoid class which can help improve performance.
		// 3. By extending the CollectionMonoid class which helps reducing the effort
		//    needed to define such a monoid.
		// 4. By implementing the Commutative interface so that the monoid can be used
		//    in parallel comprehensions.
		// 5. A discussion shows how to define a monoid that does not build any explicit
		//    value.
		// 6. A discussion shows how to define an efficient monoid on primitive types.
		System.out.println("UserDefinedMonoid SAMPLE");
		System.out.println("------------------------");
		System.out.println();
		
		// WHAT IS A MONOID
		// ----------------
		// First, let's explain briefly what a monoid is.
		//
		// A monoid is the "operator" used in comprehensions as shown in the Basics sample.
		//
		// In Ateji PX, a monoid is a subtype of the abstract class:
		//     apx.util.monoid.Monoid<C, E>
		// Defining a new monoid is a matter of extending the class Monoid<C, E>.
		//
		// The type parameters C and E of a Monoid corresponds respectively to the type
		// of the results built by the monoid and to the type of the elements processed by
		// the monoid; C is called the "collection type" and E is called the "element type".
		/**
		 * In our case we're building sets of String from String values so it is quite
		 * straightforward: C is java.util.Set<String> and E is String.
		 */
		class StringSetMonoid
		extends Monoid<Set<String>, String> {
			
			// MONOID METHODS
			// --------------
			// Now about the methods that must be implemented when defining a new monoid.
			// There is three methods that must be implemented plus a fourth method that
			// already has a default implementation but can be overriden for a more
			// efficient implementation.
			//
			// These methods are: zero, unit, merge and add (optional).
			//
			// HOW A COMPREHENSION USE MONOIDS: When running a sequential comprehension,
			// a variable called accumulator will first be created. It is initialized by
			// the neutral element, that is by a call to the zero method. Then for each
			// iterated element x of the comprehension the accumulator variable will be
			// updated with the result of a call to the add method: 
			//     accumulator = add(accumulator, x);
			// Unless the add has been overridden, the add method relies on the merge and
			// unit methods:
			//     add(accumulator, x)  ->  merge(accumulator, unit(x))
			//
			// PARALLEL COMPREHENSIONS: The merge method is also used in parallel
			// comprehensions; a parallel comprehension runs several sequential
			// comprehensions in parallel and then merges the result of each of these
			// sequential comprehensions.

			// The merge method defines how to merge two collections into one.
			// This method must be associative, that is merge(a, merge(b, c) and
			// merge(merge(a, b), c) must return the same result.
			/**
			 * In our case, we're working on Set<String>, thus we have to merge together
			 * two distinct Set<String> objects; we're doing the union of both sets.
			 * We'll implement this method by adding all the elements from one of the
			 * two sets to the other and then returning it.
			 */
			@Override
			public Set<String> merge(Set<String> set1, Set<String> set2) {
				set1.addAll(set2); // adding all elements of set2 into set1
				return set1; // return set1, containing all elements of set1 and set2
			}
			
			// The zero method must return the neutral element of the monoid.
			// The neutral element is the element such that its application to an
			// arbitrary collection returns the same collection, that is
			// merge(zero(), c) always returns c for any collection c.
			/**
			 * In our case, we're working of Set<String>, thus we will build an empty
			 * Set<String>. We'll use HashSet<String> as the concrete implementation of
			 * Set<String>.
			 */
			@Override
			public Set<String> zero() {
				return new HashSet<String>(); // the empty set
			}
			
			// Given an element, the unit method returns the corresponding collection
			// with this element.
			/**
			 * In our case, we making Set<String> from String, thus we will build and
			 * return a new Set<String> containing the element also called singleton.
			 * As previously chosen for the zero method, we'll use HashSet<String> as
			 * the concrete implementation of Set<String>.
			 */
			@Override
			public Set<String> unit(String s) {
				Set<String> result = new HashSet<String>(); // make a new set
				result.add(s); // add the element
				return result; // return the set with one element
			}
			
			// Finally, the add method defines how to directly add one element to a
			// collection.
			//
			// Overriding this method is optional. By default the add method calls the
			// merge and unit methods:
			//     add(accumulator, x)  ->  merge(accumulator, unit(x))
			// Since the add method is at the heart of the comprehensions, providing a
			// more efficient implementation can be important.
			/**
			 * In our case, the default implementation creates a singleton and then
			 * merges it with the accumulator. But adding an additional String element
			 * to an existing Set<String> is faster than this default implementation.
			 * It is a typical case where the add method should be overriden.
			 */
			@Override
			public Set<String> add(Set<String> accumulator, String s) {
				accumulator.add(s); // add the element
				return accumulator; // return the updated set
			}
		}
		
		// We can now use the newly-defined monoid into a comprehension. Let's define
		// some sample data to test this new monoid.
		// Note that "Patrick" appears twice in this array but should occur only
		// once in the resulting sets built by the monoid.
		String[] data = {
				"Patrick", "Denis", "Jean-Pierre", "Kevin", "Patrick", "Laurent",
		};
		System.out.println("Sample data used:");
		System.out.println("\tdata = " + Arrays.toString(data));
		System.out.println();		
		System.out.println("Using the user-defined StringSetMonoid to build a set of String:");
		java . lang . String freshVariable32 ; {
freshVariable32 ="\tnew StringSetMonoid() for (String s : data) s  ->  "
				; }
StringSetMonoid monoid15 = new StringSetMonoid() ; java . util . Set < java . lang . String > toBeReturned7 = monoid15 . zero ( ) ; for ( String s : data) {
toBeReturned7 = monoid15 . add ( toBeReturned7 , s) ; }
System.out.println(freshVariable32 + toBeReturned7 );
		System.out.println();
		
		// MUTABLE MONOID
		// --------------
		// You may have noticed that in the implementation of the merge and add methods,
		// the collection is modified and then returned. In other words, the monoid is
		// working on mutable objects that are modified instead of always returning the
		// merges collection.
		//
		// This property is enforced by a subclass of Monoid:
		//     apx.util.monoid.MutableMonoid<C, E>
		//       extends Monoid<C, E>
		// As a sub-type of Monoid, a MutableMonoid is still a monoid and can be used in
		// all the same situations. Also the type parameters C and E serves the same
		// purpose as for Monoid<C, E>.
		//
		// Besides, when used as a MutableMonoid, the Ateji PX compiler optimizes the use
		// of a MutableMonoid.
		// HOW A COMPREHENSION USE MUTABLE MONOIDS: When running a sequential
		// comprehension on a MutableMonoid, the accumulator variable is updated by
		// changing its state in the mutableAdd method:
		//     mutableAdd(accumulator, x);
		/**
		 * We're still building sets of String from String values so it's the same:
		 * C is java.util.Set<String> and E is String.
		 */
		class StringSetMutableMonoid
		extends MutableMonoid<Set<String>, String> {

			// MUTABLE MONOID METHODS
			// ----------------------
			// The zero and unit methods from Monoid must still be implemented and serves
			// the same purpose.
			//
			// The merge and add methods are now implemented by MutableMonoid and are
			// replaced by the mutableMerge and mutableAdd methods. These implementation
			// simply call the new mutableMerge and mutableAdd methods:
			//     add(accumulator, x)  ->  mutableAdd(accumulator, x); return accumulator
			//     merge(a, b)          ->  mutableMerge(a, b); return a
			// The mutableMerge method must preserve the associativity of the merge method.
			//
			// As with the add method of Monoid, the mutableAdd method of MutableMonoid
			// already has a default implementation which can be overridden for a more
			// efficient implementation:
			//     mutableAdd(accumulator, x)  ->  mutableMerge(accumulator, unit(x))
			
			/**
			 * Same implementation as for StringSetMonoid.
			 */
			@Override
			public Set<String> zero() {
				return new HashSet<String>();
			}
			
			/**
			 * Same implementation as for StringSetMonoid.
			 */
			@Override
			public Set<String> unit(String s) {
				Set<String> result = new HashSet<String>();
				result.add(s);
				return result;
			}

			// The mutableMerge method defines how to merge a collection into the
			// accumulator.
			// By convention, the accumulator is the first argument of the mutableMerge
			// method.
			/**
			 * Merging two sets consist to compure their union. The result must be stored
			 * in the first argument, which is the accumulator.
			 * We'll implement this method by adding all the elements from the second
			 * argument into the accumulator.
			 */
			@Override
			public void mutableMerge(Set<String> accumulator, Set<String> newSet) {
				accumulator.addAll(newSet); // adding all elements of newSet into the accumulator
			}

			// The mutableAdd method defines how to directly add one element to an
			// accumulator collection.
			//
			// Overriding this method is optional. By default the mutableAdd method calls
			// the mutableMerge and unit methods:
			//     mutableAdd(accumulator, x)  ->  mutableMerge(accumulator, unit(x))
			// Since the mutableAdd method is at the heart of the comprehensions, providing
			// a more efficient implementation can be important.
			/**
			 * In our case, the default implementation creates a singleton and then merges
			 * it with the accumulator. But adding an additional String element to an
			 * existing Set<String> is faster than this default implementation. 
			 * It is a typical case where the add method should be overridden.
			 */			
			@Override
			public void mutableAdd(Set<String> accumulator, String s) {
				accumulator.add(s); // add the element
			}
		}
		// We can now use the newly-defined mutable monoid into a comprehension.
		System.out.println("Using the user-defined StringSetMutableMonoid to build a set of String:");
		java . lang . String freshVariable33 ; {
freshVariable33 ="\tnew StringSetMutableMonoid() for (String s : data) s  ->  "
				; }
StringSetMutableMonoid monoid16 = new StringSetMutableMonoid() ; java . util . Set < java . lang . String > toBeReturned8 = monoid16 . zero ( ) ; for ( String s : data) {
monoid16 . mutableAdd ( toBeReturned8 , s) ; }
System.out.println(freshVariable33 + toBeReturned8 );
		System.out.println();

		// COLLECTION MONOID
		// -----------------
		// You may have noticed that the unit, mutableMerge and mutableAdd methods mostly
		// uses methods from the java.util.Collection API. The only specific part is how to
		// create an empty collection.
		//
		// Sharing this common code is exactly what is doing the CollectionMonoid abstract
		// class:
		//    apx.util.monoid.CollectionMonoid<C extends java.util.Collection<E>, E>
		//       extends MutableMonoid<C, E>
		//
		// A CollectionMonoid is still a monoid and can be used in comprehension expressions.
		// Also the type parameters C and E serves the same purpose as for
		// MutableMonoid<C, E>; it however enforces that C must be a Java Collection. 
		/**
		 * We're still building sets of String from String values so nothing change:
		 * C is java.util.Set<String> and E is String.
		 * 
		 * Note that Set<String> is a sub-type of java.util.Collection<String> and thus can
		 * be used as the C type parameter of CollectionMonoid.
		 */
		class StringSetCollectionMonoid
		extends CollectionMonoid<Set<String>, String> {

			// COLLECTION MONOID METHODS
			// -------------------------
			// Most methods are already implemented by CollectionMonoid: mutableMerge and
			// mutableAdd uses java.util.Collection methods; unit uses the zero method to
			// build an empty collection and then uses the java.util.Collection add method.
			//
			// So only one method needs to be implemented: zero; instantiation of empty
			// collections must be defined.
			
			/**
			 * Same implementation as for StringSetMonoid.
			 */
			@Override
			public Set<String> zero() {
				return new HashSet<String>();
			}
		}
		// We can now use the newly-defined collection monoid into a comprehension.
		System.out.println("Using the user-defined StringSetCollectionMonoid to build a set of String:");
		java . lang . String freshVariable34 ; {
freshVariable34 ="\tnew StringSetCollectionMonoid() for (String s : data) s  ->  "
				; }
StringSetCollectionMonoid monoid17 = new StringSetCollectionMonoid() ; java . util . Set < java . lang . String > toBeReturned9 = monoid17 . zero ( ) ; for ( String s : data) {
monoid17 . mutableAdd ( toBeReturned9 , s) ; }
System.out.println(freshVariable34 + toBeReturned9 );
		System.out.println();

		// COMMUTATIVE MONOID
		// ------------------
		// Until then, none of these monoids have been used in a parallel comprehension.
		// That's because the Ateji PX compiler forbids it: parallel comprehensions are
		// restricted to commutative monoids.
		//
		// COMMUTATIVITY IN PARALLEL COMPREHENSIONS: Running a parallel comprehension
		// corresponds to running several sequential comprehensions in parallel and then
		// merging their respective results. The order in which the sequential
		// comprehensions will finish and thus the order in which the merges will be
		// done is non-deterministic. Then in order to obtain a deterministic result the 
		// merge method has to be commutative. That way the order in which the merge calls 
		// are done is not important.
		//
		// A commutative monoid is a monoid whose merge method is commutative: 
		// merge(a, b) and merge(b, a) both return the same result. 
		//
		// In Ateji PX, a monoid is commutative if it is a subtype of the interface:
		//     apx.util.Commutative
		// Defining a commutative monoid is a matter of implementing the interface
		// Commutative.
		/** 
		 * The merge operation of the monoid is commutative because it is working on
		 * sets, which are unordered; adding a then b results in the same set than
		 * adding b then a.
		 *
		 * We are defining the same monoid than StringSetCollectionMonoid but we are also 
		 * implementing the Commutative interface. This monoid can thus be used in a 
		 * parallel comprehension.
		 */
		class StringSetCommutativeMonoid
		extends CollectionMonoid<Set<String>, String>
		implements Commutative {
			/**
			 * Same implementation as for StringSetMonoid.
			 */
			@Override
			public Set<String> zero() {
				return new HashSet<String>();
			}
		}
		// We can now use the newly-defined commutative monoid into a comprehension,
		// but also into a parallel comprehension.
		System.out.println("Using the user-defined StringSetCommutativeMonoid to build a set of String:");
		java . lang . String freshVariable35 ; {
freshVariable35 ="\tnew StringSetCommutativeMonoid() for (String s : data) s  ->  "
				; }
StringSetCommutativeMonoid monoid18 = new StringSetCommutativeMonoid() ; java . util . Set < java . lang . String > toBeReturned10 = monoid18 . zero ( ) ; for ( String s : data) {
monoid18 . mutableAdd ( toBeReturned10 , s) ; }
System.out.println(freshVariable35 + toBeReturned10 );
		java . lang . String freshVariable36 ; {
freshVariable36 ="\tnew StringSetCommutativeMonoid() for || (String s : data) s  ->  "
				; }
final java . lang . Object lock0 = new java . lang . Object ();
final StringSetCommutativeMonoid monoid0 = new StringSetCommutativeMonoid() ;
java . util . Set < java . lang . String > global0 = monoid0 . zero ( ) ;
final java . lang . String [ ] array0 = data; final int blockcount0 = java . lang . Runtime .getRuntime ().availableProcessors (); {
final apx . lang . gen . MutableReference < java . util . Set < java . lang . String > > global00 = new apx . lang . gen . MutableReference < java . util . Set < java . lang . String > > (global0 );
{
final java . util . List < apx . lang . gen . Branch > branches5 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs5 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock5 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range36 = new apx . util . RangeInteger (0 , ((blockcount0 )- 1 ));
final int last27 = (range36 ). max ;
for(int block25 = (range36 ). min ;block25 <= last27 ;block25 ++ )
{
final int nbBlock0 = block25 ;
{
{
apx . lang . gen . Branch branch6 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
StringSetCommutativeMonoid monoid19 = monoid0 ; java . util . Set < java . lang . String > toBeReturned11 = monoid19 . zero ( ) ; {
final apx . util . RangeInteger range37 = new apx . util . RangeInteger ((((new apx . util . RangeInteger ((0 ), ((array0 . length )- 1 ))). min )+ ((((new apx . util . RangeInteger ((0 ), ((array0 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array0 . length )- 1 ))). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((new apx . util . RangeInteger ((0 ), ((array0 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array0 . length )- 1 ))). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* (nbBlock0 )), (java . lang . Math . min (((new apx . util . RangeInteger ((0 ), ((array0 . length )- 1 ))). max ), ((new apx . util . RangeInteger ((0 ), ((array0 . length )- 1 ))). min )+ ((((new apx . util . RangeInteger ((0 ), ((array0 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array0 . length )- 1 ))). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((new apx . util . RangeInteger ((0 ), ((array0 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array0 . length )- 1 ))). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* ((nbBlock0 )+ 1 )- 1 )));
final int last28 = (range37 ). max ;
for(int block26 = (range37 ). min ;block26 <= last28 ;block26 ++ )
{
final int arrayIndex = block26 ;
{
{ String s = array0 [ arrayIndex ] ; monoid19 . mutableAdd ( toBeReturned11 , (s)) ; } }
}
}
final java . util . Set < java . lang . String > local0 = toBeReturned11 ;
synchronized ( lock0 ) { monoid0 . mutableMerge ( global00 .ref , local0 ) ; } }
}
;
branches5 .add (branch6 );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus6 =parallelBlock5 . run (branches5 , bangs5 );
global0 = global00 .ref ;
if(exitStatus6 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable6 =exitStatus6 .thrownException ();
if(throwable6 !=null) {
if(throwable6 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable6 ;
if(throwable6 instanceof java . lang . Error ) throw (java . lang . Error )throwable6 ;
}
}
}
}
System.out.println(freshVariable36 + global0 );
		System.out.println();
		
		// VOID MONOIDS
		// ------------
		// It is possible to define a monoid whose only purpose is to make side-effects.
		// Such a monoid is called a void monoid: a monoid which actually do not build
		// any collection.
		//
		// This is the purpose of the VoidMonoid:
		//     apx.util.monoid.VoidMonoid<E>
		//       extends MutableMonoid<Void, E>
		//
		// A VoidMonoid is still a monoid and can be used in comprehension expressions.
		// VoidMonoid<E> has only one type parameter E: the type of the elments, which
		// serves the same purpose as for MutableMonoid<C, E>; on the other hand there
		// is no C type parameter because a VoidMonoid does not build any collection.
		
		// Here is the definition of a printer monoid which prints String onto the
		// standard output System.out.
		/**
		 * The void monoid prints String values onto System.out: E is String.
		 */
		class SysOutPrintMonoid
		extends VoidMonoid<String> {

			// VOID MONOID METHODS
			// -------------------
			// Only one method must be implemented for VoidMonoid sub-classes: voidUnit.			
			//
			// Since mutableMerge do not build any collection, mutableAdd can be reduced
			// to a call to unit:
			//     mutableAdd(accumulator, x)  ->  mutableMerge(accumulator, unit(x))
			//                                 ->  unit(x)
			// For the same reason, the unit method always return null. The unit method
			// is replaced by the voidUnit method. The implementation of unit simply
			// calls the voidUnit method:
			//     unit(x)  ->  voidUnit(x); return null
			
			/**
			 * Prints s on System.out.
			 */			
			@Override
			public void voidUnit(String s) {
				System.out.println("\t\t"+s); // we add tabulations for a nicer print
			}
		}
		// We can now use the newly-defined void monoid into a comprehension.
		System.out.println("Using the user-defined SysOutPrintMonoid to print the data:");
		System.out.println("\tnew SysOutPrintMonoid() for (String s : data) s;");
		{ SysOutPrintMonoid monoid20 = new SysOutPrintMonoid() ; java . lang . Void toBeReturned12 = monoid20 . zero ( ) ; for ( String s : data) {
monoid20 . mutableAdd ( toBeReturned12 , s) ; }
} System.out.println();
		
		// PRIMITIVE MONOIDS
		// -----------------
		// The Monoid class can not be used with primtive types, because the type 
		// parameters of Monoid<C, E> must be reference types.  Using primitive types 
		// relies on wrapper types (for instance Integer for int) which can lead to a 
		// significant overhead at run-time.
		//
		// For this specific case,  primitive monoids can be used. A primitive monoid 
		// has the following properties:
		// 1. the collection type and the element type are both the same;
		// 2. the collection/element type is a primitive type; and
		// 3. there is no unit method because unit must be the identity, that is
		//    unit(x) is x.
		//
		// In Ateji PX, a primitive monoid is a subtype of any of these interfaces: 
		//     apx.util.monoid.PrimitiveBooleanMonoid
		//     apx.util.monoid.PrimitiveByteMonoid
		//     apx.util.monoid.PrimitiveCharMonoid
		//     apx.util.monoid.PrimitiveDoubleMonoid
		//     apx.util.monoid.PrimitiveFloatMonoid
		//     apx.util.monoid.PrimitiveIntMonoid
		//     apx.util.monoid.PrimitiveLongMonoid
		//     apx.util.monoid.PrimitiveShortMonoid
		// Defining a new primitive monoid is a matter of implementing any of these
		// interfaces.
		
		// Here is the definition of some basic sum monoid that can work on both
		// int and long values.
		/**
		 * This sum monoid can work on int and long values; thus it implements the
		 * PrimitiveIntMonoid and PrimitiveLongMonoid interfaces. Besides it is
		 * also commutative: thus it implements Commutative.
		 */
		class IntegralSumMonoid
		implements PrimitiveIntMonoid, PrimitiveLongMonoid, Commutative {

			// PRIMITIVE MONOID METHODS
			// ------------------------
			// Much like for the previously shown monoids, the zero and merge
			// methods must be implemented, with the constraint that the merge
			// method must be associative.
			//
			// On the other hand, no unit or add method has to be implemented 
			// since unit is the identity; the add method and merge method are
			// the same in that case:
			//     add(a, x)  ->  merge(a, unit(x))  ->  merge(a, x)
			//
			// These zero and merge methods have specific names according to
			// their belonging interface; here the zero and merge methods of
			// PrimitiveIntMonoid are called intZero and intMerge while those
			// of PrimitiveLongMonoid are called longZero and longMerge.
			
			/**
			 * The neutral element of the sum is 0.
			 */
			public int intZero() {
				return 0;
			}

			/**
			 * We're defining sum: merge(a, b) is a+b.
			 * Note that this operation is commutative, hence the
			 * implementation of the Commutative interface.
			 */
			public int intMerge(int a, int b) {
				return a+b;
			}

			/**
			 * The neutral element of the sum is 0.
			 * As we're working on long values, we'll return 0L.
			 */
			public long longZero() {
				return 0L;
			}

			/**
			 * We're defining sum: merge(a, b) is a+b.
			 * Note that this operation is commutative, hence the
			 * implementation of the Commutative interface.
			 */
			public long longMerge(long a, long b) {
				return a+b;
			}
		}
		// We can now use the newly-defined primitive monoid into a comprehension,
		// but also into a parallel comprehension since it is commutative.
		System.out.println("Using the user-defined IntegralSumMonoid to sum the length of the String:");
		java . lang . String freshVariable37 ; {
freshVariable37 ="\tnew IntegralSumMonoid() for (String s : data) s  ->  "
				; }
IntegralSumMonoid monoid21 = new IntegralSumMonoid() ; int toBeReturned13 = monoid21 . intZero ( ) ; for ( String s : data) {
toBeReturned13 = monoid21 . intMerge ( toBeReturned13 , s.length()) ; }
System.out.println(freshVariable37 + toBeReturned13 );
		java . lang . String freshVariable38 ; {
freshVariable38 ="\tnew IntegralSumMonoid() for || (String s : data) s  ->  "
				; }
final java . lang . Object lock1 = new java . lang . Object ();
final IntegralSumMonoid monoid1 = new IntegralSumMonoid() ;
int global1 = monoid1 . intZero ( ) ;
final java . lang . String [ ] array1 = data; final int blockcount1 = java . lang . Runtime .getRuntime ().availableProcessors (); {
final apx . lang . gen . MutableReferenceInt global10 = new apx . lang . gen . MutableReferenceInt (global1 );
{
final java . util . List < apx . lang . gen . Branch > branches6 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs6 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock6 =apx . lang . gen . Parallel .getParallelBlock (null);
{
final apx . util . RangeInteger range38 = new apx . util . RangeInteger (0 , ((blockcount1 )- 1 ));
final int last29 = (range38 ). max ;
for(int block27 = (range38 ). min ;block27 <= last29 ;block27 ++ )
{
final int nbBlock1 = block27 ;
{
{
apx . lang . gen . Branch branch7 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
IntegralSumMonoid monoid22 = monoid1 ; int toBeReturned14 = monoid22 . intZero ( ) ; {
final apx . util . RangeInteger range39 = new apx . util . RangeInteger ((((new apx . util . RangeInteger ((0 ), ((array1 . length )- 1 ))). min )+ ((((new apx . util . RangeInteger ((0 ), ((array1 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array1 . length )- 1 ))). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((new apx . util . RangeInteger ((0 ), ((array1 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array1 . length )- 1 ))). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* (nbBlock1 )), (java . lang . Math . min (((new apx . util . RangeInteger ((0 ), ((array1 . length )- 1 ))). max ), ((new apx . util . RangeInteger ((0 ), ((array1 . length )- 1 ))). min )+ ((((new apx . util . RangeInteger ((0 ), ((array1 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array1 . length )- 1 ))). min )+ 1 )/ (java . lang . Runtime .getRuntime ().availableProcessors ())+ ((((new apx . util . RangeInteger ((0 ), ((array1 . length )- 1 ))). max )- ((new apx . util . RangeInteger ((0 ), ((array1 . length )- 1 ))). min )+ 1 )% (java . lang . Runtime .getRuntime ().availableProcessors ())== 0 ? 0 : 1 ))* ((nbBlock1 )+ 1 )- 1 )));
final int last30 = (range39 ). max ;
for(int block28 = (range39 ). min ;block28 <= last30 ;block28 ++ )
{
final int arrayIndex0 = block28 ;
{
{ String s = array1 [ arrayIndex0 ] ; toBeReturned14 = monoid22 . intMerge ( toBeReturned14 , (s.length())) ; } }
}
}
final int local1 = toBeReturned14 ;
synchronized ( lock1 ) { global10 .ref = monoid1 . intMerge ( global10 .ref , local1 ) ; } }
}
;
branches6 .add (branch7 );
}
}
}
}
final apx . lang . gen . ExitStatus exitStatus7 =parallelBlock6 . run (branches6 , bangs6 );
global1 = global10 .ref ;
if(exitStatus7 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable7 =exitStatus7 .thrownException ();
if(throwable7 !=null) {
if(throwable7 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable7 ;
if(throwable7 instanceof java . lang . Error ) throw (java . lang . Error )throwable7 ;
}
}
}
}
System.out.println(freshVariable38 + global1 );
	}
	
}
