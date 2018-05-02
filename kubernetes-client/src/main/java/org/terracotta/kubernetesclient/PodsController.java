package org.terracotta.kubernetesclient;

import com.google.gson.JsonSyntaxException;
import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1DeleteOptions;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.util.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Controller
public class PodsController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PodsController.class);

  @GetMapping("/")
  public String listPods(Model model) {
    List<V1Pod> pods = null;
//list
    try {
      ApiClient client = Config.defaultClient();
      Configuration.setDefaultApiClient(client);
      CoreV1Api api = new CoreV1Api();
      V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
      pods = list.getItems();

    } catch (IOException | ApiException exception) {
      LOGGER.info("There was an issue : ", exception);
    }

    model.addAttribute("pods", pods);

    return "pods";
  }

  @PostMapping("/")
  public RedirectView deletePod(@RequestParam(value = "name") String name, Model model) {
    try {
      ApiClient client = Config.defaultClient();
      Configuration.setDefaultApiClient(client);
      CoreV1Api api = new CoreV1Api();
      V1DeleteOptions v1DeleteOptions = new V1DeleteOptions();
      api.deleteNamespacedPod(name, "default", v1DeleteOptions, null, null, false, null);
    } catch (JsonSyntaxException exception) {
      LOGGER.info("There was an issue with the response parsing : ", exception);
    } catch (IOException | ApiException exception) {
      LOGGER.info("There was an issue : ", exception);
    }

    return new RedirectView("/");

  }
}