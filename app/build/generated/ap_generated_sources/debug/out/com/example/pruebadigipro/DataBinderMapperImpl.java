package com.example.pruebadigipro;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(0);

  static {
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(7);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    result.add(new com.digipro.fesdkcore.DataBinderMapperImpl());
    result.add(new com.digipro.fesdkcore.elementos_anexo.DataBinderMapperImpl());
    result.add(new com.digipro.fesdkcore.elementos_basicos.DataBinderMapperImpl());
    result.add(new com.digipro.fesdkcore.elementos_huella.DataBinderMapperImpl());
    result.add(new com.digipro.fesdkcore.elementos_rostro.DataBinderMapperImpl());
    result.add(new com.manojbhadane.genericadapter.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(22);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "elemento");
      sKeys.put(2, "handlers");
      sKeys.put(3, "bindings");
      sKeys.put(4, "noElemento");
      sKeys.put(5, "viewHolder");
      sKeys.put(6, "anexo");
      sKeys.put(7, "plantilla");
      sKeys.put(8, "textreadvideo");
      sKeys.put(9, "anexoBindings");
      sKeys.put(10, "locale");
      sKeys.put(11, "pagina");
      sKeys.put(12, "log");
      sKeys.put(13, "executeServicesInterface");
      sKeys.put(14, "synclog");
      sKeys.put(15, "findElementByIdInterface");
      sKeys.put(16, "typeSelected");
      sKeys.put(17, "logRegla");
      sKeys.put(18, "textanchors");
      sKeys.put(19, "fila");
      sKeys.put(20, "propiedades");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(0);

    static {
    }
  }
}
