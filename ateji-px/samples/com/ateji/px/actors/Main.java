/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.px.actors;

import apx.lang.AsyncChan;
import apx.lang.IChan;
import apx.lang.serial.SerializationCloner;

/**
 * An actor application is broken up into completely independent processes (Actors). 
 * They communicate with each other through messages, thus eliminating shared state, and thus 
 * eliminating problems of data corruption and deadlocks.
 * 
 * The Main class instantiates and passes in Mailboxes to the Actors. Each actor gets 
 * a reference to its Mailbox.
 * 
 * In this sample, three actors form a pipeline
 *  - an actor downloads pages from the world wide web, 
 *  - an actor indexes them into a multimap 
 *  - and then an actor writes the multimap into a database table.
 *  
 *  How to read this example
 *  ------------------------
 *   - To understand how to implement an actor with Ateji PX, refer to the Actor class,
 *   - To understand how the actions described below are implemented, refer to the 
 *     DownloadActor, IndexActor and DatabaseActor classes.
 */
public class Main {

	public static void main(String[] args){

		
		String[] sitesToIndex = {
				"http://www.ateji.com/",
				"http://ateji.blogspot.com/",
				"http://forums.ateji.com/",
		};
		
		IChan<Message> downloadActorMailBox = new AsyncChan<Message>(new SerializationCloner<Message>());
		IChan<Message> indexActorMailBox = new AsyncChan<Message>(new SerializationCloner<Message>());
		IChan<Message> dataBaseActorMailBox = new AsyncChan<Message>(new SerializationCloner<Message>());
		DatabaseActor writeActor = new DatabaseActor(dataBaseActorMailBox);
		IndexActor indexActor = new IndexActor(indexActorMailBox, writeActor);
		DownloadActor downloadActor = new DownloadActor(downloadActorMailBox, indexActor);		
		{
final com . ateji . px . actors . DownloadActor downloadActor0 = downloadActor ;
final com . ateji . px . actors . DatabaseActor writeActor0 = writeActor ;
final com . ateji . px . actors . IndexActor indexActor0 = indexActor ;
final apx . lang . IChan < com . ateji . px . actors . Message > downloadActorMailBox0 = downloadActorMailBox ;
final java . lang . String [ ] sitesToIndex0 = sitesToIndex ;
{
		final java . util . List < apx . lang . gen . Branch > branches32 =new java . util . ArrayList < apx . lang . gen . Branch > ();
final java . util . List < apx . lang . gen . Bang < ? > > bangs32 =new java . util . ArrayList < apx . lang . gen . Bang < ? > > ();
final apx . lang . gen . Parallel parallelBlock32 =apx . lang . gen . Parallel .getParallelBlock (null);
{
apx . lang . gen . Branch branch45 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
writeActor0.run();
		}
}
;
branches32 .add (branch45 );
}
{
apx . lang . gen . Branch branch46 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
downloadActor0.run();
		}
}
;
branches32 .add (branch46 );
}
{
apx . lang . gen . Branch branch47 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
indexActor0.run();
		}
}
;
branches32 .add (branch47 );
}
{
apx . lang . gen . Branch branch48 = new apx . lang . gen . Branch (null){
public @java .lang .Override void run () throws java . lang . Throwable {
for ( String siteToIndex : sitesToIndex0) {
{
				 downloadActorMailBox0 . send (new Message("download", siteToIndex));
			}
			}
downloadActorMailBox0 . send (Message.stopMessage);
		}
}
;
branches32 .add (branch48 );
}
final apx . lang . gen . ExitStatus exitStatus36 =parallelBlock32 . run (branches32 , bangs32 );
if(exitStatus36 .hasReturned ()) {
return ;
} {
final java . lang . Throwable throwable36 =exitStatus36 .thrownException ();
if(throwable36 !=null) {
if(throwable36 instanceof java . lang . RuntimeException ) throw (java . lang . RuntimeException )throwable36 ;
if(throwable36 instanceof java . lang . Error ) throw (java . lang . Error )throwable36 ;
}
}
}
				
	}
}
	
	
	
}
