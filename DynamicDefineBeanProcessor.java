package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class DynamicDefineBeanProcessor implements BeanFactoryPostProcessor   {

	private static final Logger logger = LoggerFactory.getLogger(DynamicDefineBeanProcessor.class);

	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		logger.info("postProcessBeanFactory start");
		GenericBeanDefinition  definition = new GenericBeanDefinition ();
		definition.setParentName("baseMyBatisDao");
		
		((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("userDao", definition);
		logger.info("postProcessBeanFactory end");
	}

}
