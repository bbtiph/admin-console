package kaz.post.crmserver.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.*;

public final class XMLUtil {

	private static final Logger log = LoggerFactory.getLogger(XMLUtil.class);

	public static Map<String, String> getXMLValuesByTag(String xmlString, String rootElement, List<String> valuesList) {
		return getXMLValuesCommon(xmlString, rootElement, null, valuesList);
	}

	public static Map<String, String> getXMLValuesByTagAndNamespace(String xmlString, String rootElement, String nsUrl, List<String> valuesList) {
		return getXMLValuesCommon(xmlString, rootElement, nsUrl, valuesList);
	}

	public static List<Map<String, String>> getXMLChildValues(String xmlString, String rootElement, String nsUrl, String childElement, List<String> valuesList) {
		return getXMLChildValuesCommon(xmlString, rootElement, nsUrl, childElement, valuesList);
	}

	private static Map<String, String> getXMLValuesCommon(String xmlString, String rootElement, String nsUrl, List<String> valuesList) {
		try {
			Map<String, String> resultMap = new HashMap<>();
			if (Objects.isNull(valuesList)) {
				valuesList = new ArrayList<>();
				valuesList.add("Res");
			}
			log.debug(xmlString);

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			Document doc = documentBuilder.parse(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));

			NodeList rootEl;
			if (Objects.isNull(nsUrl)) {
				rootEl = doc.getElementsByTagName(rootElement);
			} else {
				rootEl = doc.getElementsByTagNameNS(nsUrl, rootElement);
			}
			log.debug(String.valueOf(rootEl));

			int length = rootEl.getLength();
			log.debug(String.valueOf(rootEl.getLength()));

			for (int i = 0; i < length; i++) {
				Node eNode = rootEl.item(i);
				if (eNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) eNode;
					for (String valuesItem : valuesList) {
						NodeList elNode;
						if (Objects.isNull(nsUrl)) {
							elNode = eElement.getElementsByTagName(valuesItem);
						} else {
							elNode = eElement.getElementsByTagNameNS(nsUrl, valuesItem);
						}
						if (Objects.nonNull(elNode)
							&& Objects.nonNull(elNode.item(0))
							&& Objects.nonNull(elNode.item(0).getTextContent())) {
							log.debug("FOUND XML VALUE..");
							log.debug(valuesItem + " = " + elNode.item(0).getTextContent());
							resultMap.put(valuesItem, elNode.item(0).getTextContent());
						}
					}
				}
			}
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static List<Map<String, String>> getXMLChildValuesCommon(String xmlString, String rootElement, String nsUrl, String childElement, List<String> valuesList) {
		try {
			List<Map<String, String>> resultMapList = new ArrayList<>();
			if (Objects.isNull(valuesList)) {
				valuesList = new ArrayList<>();
				valuesList.add("Res");
			}
			if (Objects.isNull(childElement)) {
				return null;
			}
			log.debug(xmlString);

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			Document doc = documentBuilder.parse(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));

			NodeList rootEl;
			if (Objects.isNull(nsUrl)) {
				rootEl = doc.getElementsByTagName(rootElement);
			} else {
				rootEl = doc.getElementsByTagNameNS(nsUrl, rootElement);
			}
			log.debug(String.valueOf(rootEl));

			int length = rootEl.getLength();
			log.debug(String.valueOf(rootEl.getLength()));
			log.debug("CHILD Element:" + childElement);

			for (int i = 0; i < length; i++) {
				Node eNode = rootEl.item(i);
				if (eNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) eNode;
					log.debug("FOUND eElement:" + eNode.getNodeName());
					NodeList childEl;
					if (Objects.isNull(nsUrl)) {
						childEl = eElement.getElementsByTagName(childElement);
					} else {
						childEl = eElement.getElementsByTagNameNS(nsUrl, childElement);
					}
					if (Objects.isNull(childEl) || childEl.getLength() < 1) {
						log.error("NOT FOUND ELEMENT WITH NAME " + childElement);
						return null;
					}
					Element childListElement = (Element) childEl.item(0);
					NodeList childElList = childListElement.getChildNodes();
					int childLength = childEl.getLength();
					log.debug(String.valueOf(childLength));
					for (int j = 0; j < childLength; j++) {
						Map<String, String> resultMap = new HashMap<>();

						Node childNode = childElList.item(j);
						log.debug("childNode = " + childNode.getNodeValue());
						log.debug("childNode = " + childNode.getTextContent());
						log.debug("childNode = " + childNode.getNodeType());

						if (childNode.getNodeType() == Node.TEXT_NODE && Objects.nonNull(childNode.getTextContent())) {
							Document childDoc = documentBuilder.parse(new ByteArrayInputStream(childNode.getTextContent().getBytes("UTF-8")));

							Node childElRootNode = childDoc.getFirstChild();

							Element child_element = (Element) childElRootNode;
							log.debug("child_element:" + child_element.getNodeName());
							for (String valuesItem : valuesList) {
								NodeList elNode = child_element.getElementsByTagName(valuesItem);
								if (Objects.nonNull(elNode)
									&& Objects.nonNull(elNode.item(0))
									&& Objects.nonNull(elNode.item(0).getTextContent())) {
									log.debug("FOUND XML VALUE..");
									log.debug(valuesItem + " = " + elNode.item(0).getTextContent());
									resultMap.put(valuesItem, elNode.item(0).getTextContent());
								}
							}
							resultMapList.add(resultMap);
						}
					}
				}
			}
			return resultMapList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
