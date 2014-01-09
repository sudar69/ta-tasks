  package ua.edu.sumdu.ta.alexandersudarenko.lab.tests;
  
  import java.util.ArrayList;
  import java.util.Collections;
  import java.util.Comparator;
  import java.util.List;
  
  import org.junit.runners.BlockJUnit4ClassRunner;
  import org.junit.runners.model.FrameworkMethod;
  import org.junit.runners.model.InitializationError;
  

  public class OrderedTestRunner extends BlockJUnit4ClassRunner {

      public OrderedTestRunner(final Class<?> klass) throws InitializationError {
          super(klass);
      }
  

      @Override
      protected List<FrameworkMethod> computeTestMethods() {
          final List<FrameworkMethod> methods = super.computeTestMethods();
          final List<FrameworkMethod> orderedMethods = new ArrayList<FrameworkMethod>(methods);
  
          Collections.sort(orderedMethods, new Comparator<FrameworkMethod>() {
              @Override
              public int compare(final FrameworkMethod m1, final FrameworkMethod m2) {
                  final Order o1 = m1.getAnnotation(Order.class);
                  final Order o2 = m2.getAnnotation(Order.class);
  
                  if (o1 == null) {
                      return -1;
                  }
                  if (o2 == null) {
                      return 1;
                  }
  
                  return o1.value() - o2.value();
              }
          });
  
          return orderedMethods;
      }
  }