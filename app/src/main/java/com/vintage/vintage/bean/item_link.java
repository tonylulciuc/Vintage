package com.vintage.vintage.bean;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class item_link
{
  private String item_id;
  private String link;
  private java.util.Date created;
  private String objectId;
  private String ownerId;
  private java.util.Date updated;
  public String getItem_id()
  {
    return item_id;
  }

  public void setItem_id( String item_id )
  {
    this.item_id = item_id;
  }

  public String getLink()
  {
    return link;
  }

  public void setLink( String link )
  {
    this.link = link;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

                                                    
  public item_link save()
  {
    return Backendless.Data.of( item_link.class ).save( this );
  }

  public Future<item_link> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<item_link> future = new Future<item_link>();
      Backendless.Data.of( item_link.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<item_link> callback )
  {
    Backendless.Data.of( item_link.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( item_link.class ).remove( this );
  }

  public Future<Long> removeAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Long> future = new Future<Long>();
      Backendless.Data.of( item_link.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( item_link.class ).remove( this, callback );
  }

  public static item_link findById( String id )
  {
    return Backendless.Data.of( item_link.class ).findById( id );
  }

  public static Future<item_link> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<item_link> future = new Future<item_link>();
      Backendless.Data.of( item_link.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<item_link> callback )
  {
    Backendless.Data.of( item_link.class ).findById( id, callback );
  }

  public static item_link findFirst()
  {
    return Backendless.Data.of( item_link.class ).findFirst();
  }

  public static Future<item_link> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<item_link> future = new Future<item_link>();
      Backendless.Data.of( item_link.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<item_link> callback )
  {
    Backendless.Data.of( item_link.class ).findFirst( callback );
  }

  public static item_link findLast()
  {
    return Backendless.Data.of( item_link.class ).findLast();
  }

  public static Future<item_link> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<item_link> future = new Future<item_link>();
      Backendless.Data.of( item_link.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<item_link> callback )
  {
    Backendless.Data.of( item_link.class ).findLast( callback );
  }

  public static BackendlessCollection<item_link> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( item_link.class ).find( query );
  }

  public static Future<BackendlessCollection<item_link>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<item_link>> future = new Future<BackendlessCollection<item_link>>();
      Backendless.Data.of( item_link.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<item_link>> callback )
  {
    Backendless.Data.of( item_link.class ).find( query, callback );
  }
}