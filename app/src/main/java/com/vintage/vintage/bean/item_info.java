package com.vintage.vintage.bean;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class item_info
{
  private java.util.Date created;
  private Double current_price;
  private String objectId;
  private java.util.Date updated;
  private String ownerId;
  private String description;
  private String item_img_high_res;
  private Double scarcity;
  private Double original_price;
  public java.util.Date getCreated()
  {
    return created;
  }

  public Double getCurrent_price()
  {
    return current_price;
  }

  public void setCurrent_price( Double current_price )
  {
    this.current_price = current_price;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription( String description )
  {
    this.description = description;
  }

  public String getItem_img_high_res()
  {
    return item_img_high_res;
  }

  public void setItem_img_high_res( String item_img_high_res )
  {
    this.item_img_high_res = item_img_high_res;
  }

  public Double getScarcity()
  {
    return scarcity;
  }

  public void setScarcity( Double scarcity )
  {
    this.scarcity = scarcity;
  }

  public Double getOriginal_price()
  {
    return original_price;
  }

  public void setOriginal_price( Double original_price )
  {
    this.original_price = original_price;
  }

                                                    
  public item_info save()
  {
    return Backendless.Data.of( item_info.class ).save( this );
  }

  public Future<item_info> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<item_info> future = new Future<item_info>();
      Backendless.Data.of( item_info.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<item_info> callback )
  {
    Backendless.Data.of( item_info.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( item_info.class ).remove( this );
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
      Backendless.Data.of( item_info.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( item_info.class ).remove( this, callback );
  }

  public static item_info findById( String id )
  {
    return Backendless.Data.of( item_info.class ).findById( id );
  }

  public static Future<item_info> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<item_info> future = new Future<item_info>();
      Backendless.Data.of( item_info.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<item_info> callback )
  {
    Backendless.Data.of( item_info.class ).findById( id, callback );
  }

  public static item_info findFirst()
  {
    return Backendless.Data.of( item_info.class ).findFirst();
  }

  public static Future<item_info> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<item_info> future = new Future<item_info>();
      Backendless.Data.of( item_info.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<item_info> callback )
  {
    Backendless.Data.of( item_info.class ).findFirst( callback );
  }

  public static item_info findLast()
  {
    return Backendless.Data.of( item_info.class ).findLast();
  }

  public static Future<item_info> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<item_info> future = new Future<item_info>();
      Backendless.Data.of( item_info.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<item_info> callback )
  {
    Backendless.Data.of( item_info.class ).findLast( callback );
  }

  public static BackendlessCollection<item_info> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( item_info.class ).find( query );
  }

  public static Future<BackendlessCollection<item_info>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<item_info>> future = new Future<BackendlessCollection<item_info>>();
      Backendless.Data.of( item_info.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<item_info>> callback )
  {
    Backendless.Data.of( item_info.class ).find( query, callback );
  }
}