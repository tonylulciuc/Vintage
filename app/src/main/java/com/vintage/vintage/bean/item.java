package com.vintage.vintage.bean;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class item
{
  private String model;
  private java.util.Date updated;
  private String item_info_id;
  private String name;
  private String variation;
  private String objectId;
  private String ownerId;
  private String series_number;
  private Integer year;
  private String item_img_low_res;
  private java.util.Date created;
  public String getModel()
  {
    return model;
  }

  public void setModel( String model )
  {
    this.model = model;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getItem_info_id()
  {
    return item_info_id;
  }

  public void setItem_info_id( String item_info_id )
  {
    this.item_info_id = item_info_id;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public void setSeries_number(String series_number) { this.series_number = series_number; }

  public String getSeries_number(){ return (this.series_number); }

  public String getVariation()
  {
    return variation;
  }

  public void setVariation( String variation )
  {
    this.variation = variation;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public Integer getYear()
  {
    return year;
  }

  public void setYear( Integer year )
  {
    this.year = year;
  }

  public String getItem_img_low_res()
  {
    return item_img_low_res;
  }

  public void setItem_img_low_res( String item_img_low_res )
  {
    this.item_img_low_res = item_img_low_res;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

                                                    
  public item save()
  {
    return Backendless.Data.of( item.class ).save( this );
  }

  public Future<item> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<item> future = new Future<item>();
      Backendless.Data.of( item.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<item> callback )
  {
    Backendless.Data.of( item.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( item.class ).remove( this );
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
      Backendless.Data.of( item.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( item.class ).remove( this, callback );
  }

  public static item findById( String id )
  {
    return Backendless.Data.of( item.class ).findById( id );
  }

  public static Future<item> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<item> future = new Future<item>();
      Backendless.Data.of( item.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<item> callback )
  {
    Backendless.Data.of( item.class ).findById( id, callback );
  }

  public static item findFirst()
  {
    return Backendless.Data.of( item.class ).findFirst();
  }

  public static Future<item> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<item> future = new Future<item>();
      Backendless.Data.of( item.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<item> callback )
  {
    Backendless.Data.of( item.class ).findFirst( callback );
  }

  public static item findLast()
  {
    return Backendless.Data.of( item.class ).findLast();
  }

  public static Future<item> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<item> future = new Future<item>();
      Backendless.Data.of( item.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<item> callback )
  {
    Backendless.Data.of( item.class ).findLast( callback );
  }

  public static BackendlessCollection<item> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( item.class ).find( query );
  }

  public static Future<BackendlessCollection<item>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<item>> future = new Future<BackendlessCollection<item>>();
      Backendless.Data.of( item.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<item>> callback )
  {
    Backendless.Data.of( item.class ).find( query, callback );
  }
}